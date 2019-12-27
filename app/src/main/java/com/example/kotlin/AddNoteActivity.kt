package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import java.io.File

import android.util.Log
import java.io.BufferedWriter
import java.io.FileWriter

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }

    fun createNote(view: View) {

        val noteNameEditText = findViewById<EditText>(R.id.noteName)
        val noteName = noteNameEditText.text.toString()
        val noteEditText = findViewById<EditText>(R.id.note)
        val note = noteEditText.text.toString()

        var file: File = File(this.getExternalFilesDir(null), noteName+".txt")
        if (!file.exists()) {
            file.createNewFile()
            Log.d("DEBUG: ", "File did not exsist so one was created!")
        } else {
            Log.d("DEBUG: ", "File exsisted already nothing was done!")
        }

        try {
            var writer: BufferedWriter = BufferedWriter(FileWriter(file, false))
            writer.write(note)
            writer.close()
        } catch (e: SomeException) {
            Log.d("Error: ", e)
        }

        exit(view)

    }


    fun exit(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
