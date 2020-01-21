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
        val nameOfNote = intent.getStringExtra("NOTE_NAME")
        val note = storage.loadNote(nameOfNote)

        findViewById<TextView>(R.id.noteName).text = note.name
        findViewById<TextView>(R.id.note).text = note.content
    }

    fun saveNote(view: View) {
        val note = getNoteFromEditText()
        storage.saveNote(note)
        exit(view)
    }

    private fun getNoteFromEditText(): Note {
        val noteNameEditText = findViewById<EditText>(R.id.noteName)
        val noteContentEditText = findViewById<EditText>(R.id.note)
        val note = Note(noteNameEditText.text.toString(), noteContentEditText.text.toString())

        return note
    }

    fun deleteNote(view: View) {
        val note = getNoteFromEditText()
        storage.deleteNote(note)
        exit(view)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exit(findViewById(R.id.backButton))
    }

    fun exit(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}