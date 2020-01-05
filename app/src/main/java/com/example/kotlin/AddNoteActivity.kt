package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import java.io.File

import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_note.*
import java.io.BufferedWriter
import java.io.FileWriter
import java.lang.Exception


class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val nameOfNote = intent.getStringExtra(NAME_OF_NOTE_MESSAGE)

        findViewById<TextView>(R.id.noteName).apply {
            text = nameOfNote
        }
        findViewById<TextView>(R.id.note).apply {
            text = "Here is supposed to be the contents of the file."
        }
    }

    fun createNote(view: View) {

        val noteNameEditText = findViewById<EditText>(R.id.noteName)
        val noteName = noteNameEditText.text.toString()
        val noteEditText = findViewById<EditText>(R.id.note)
        val note = noteEditText.text.toString()

        var file: File = File(getApplicationContext().filesDir, noteName+".txt")
        if (!file.exists()) {
            file.createNewFile()
            Log.d("DEBUG: ", "File did not existed so one was created!")
        } else {
            Log.d("DEBUG: ", "File existed already nothing was done!")
        }

        try {
            var writer: BufferedWriter = BufferedWriter(FileWriter(file, false))
            writer.write(note)
            writer.close()
        } catch (e: Exception) {
            Log.d("Error: ", e.toString())
        }

        exit(view)

    }


    fun exit(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
