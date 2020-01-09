package com.example.anteckningar


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.util.Log
import java.lang.Exception

import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout


val NAME_OF_NOTE_MESSAGE = "name"
var nameOfNote           = String()


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var storage = Storage(getApplicationContext())
        Log.d("TEST", storage.storagePath)

        var linearLayout: LinearLayout = findViewById(R.id.noteListLayout)

        var noteNames: Array<String> = retreiveNoteNames()

        for (item in noteNames) {
            Log.d("Debug: ", item)

            var button: Button = Button(this)
            button.setText(item)
            button.setTextSize(20.0f)
            button.setGravity(Gravity.CENTER)
            button.setOnClickListener {
                nameOfNote = item
                addNote(button)
            }

            linearLayout.addView(button)
        }

    }

    fun addNote(view: View) {
        val intent = Intent(this, AddNoteActivity::class.java).apply {
            putExtra(NAME_OF_NOTE_MESSAGE, nameOfNote)
        }
        startActivity(intent)
    }

    fun retreiveNoteNames(): Array<String> {

        try {
            var files: Array<String> = getApplicationContext().fileList()
            return files
        } catch (e: Exception) {
            Log.d("Error: ", "Could not get files " + e.toString())
        }

        return emptyArray()
    }
}
