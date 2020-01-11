package com.example.anteckningar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

import android.widget.TextView
import com.example.anteckningar.entities.Note


class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        applySavedNoteToTextView()
    }

    private fun applySavedNoteToTextView() {
        val nameOfNote = intent.getStringExtra(NAME_OF_NOTE_MESSAGE)
        val note = storage.loadNote(nameOfNote)

        findViewById<TextView>(R.id.noteName).text = note.name
        findViewById<TextView>(R.id.note).text     = note.content
    }

    fun saveNote() {
        val note = getNoteFromEditText()
        storage.saveNote(note)
        exit()
    }

    private fun getNoteFromEditText(): Note {
        val noteNameEditText = findViewById<EditText>(R.id.noteName)
        val noteContentEditText = findViewById<EditText>(R.id.note)
        val note = Note(noteNameEditText.text.toString(), noteContentEditText.text.toString())

        return note
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exit()
    }

    fun exit() {
        resetNameOfNote()
        startMainActivity()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun resetNameOfNote() {
        nameOfNote = String()
    }
}
