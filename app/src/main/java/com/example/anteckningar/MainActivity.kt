package com.example.anteckningar


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.util.Log

import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout


val NAME_OF_NOTE_MESSAGE = "NAME"
var nameOfNote           = String()

lateinit var storage: Storage


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storage = Storage(getApplicationInfo().dataDir+"/files")

        var linearLayout: LinearLayout = findViewById(R.id.noteListLayout)

        //var storage = Storage(getApplicationInfo().dataDir+"/files")
        var noteNames: Array<String> = storage.listNoteNames()
        for (name in noteNames) Log.d("Test", name)

        Log.d("Content", storage.loadNote("ut.txt").content)

        for (noteName in noteNames) {
            var button: Button = Button(this)
            button.setText(noteName)
            button.setTextSize(20.0f)
            button.setGravity(Gravity.CENTER)
            button.setOnClickListener {
                nameOfNote = noteName
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
}
