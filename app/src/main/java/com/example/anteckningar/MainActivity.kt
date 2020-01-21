package com.example.anteckningar


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.util.Log

import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout


lateinit var storage: Storage


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStorage()
        loadNotes()
    }

    private fun initStorage() {
        storage = Storage(getApplicationInfo().dataDir + "/files/notes")
    }

    private fun loadNotes() {
        var linearLayout: LinearLayout = findViewById(R.id.noteListLayout)

        var noteNames: Array<String> = storage.listNoteNames()
        for (name in noteNames) Log.d("Test", name)

        for (noteName in noteNames) {
            var button = Button(this)
            button.setText(noteName)
            button.setTextSize(20.0f)
            button.setGravity(Gravity.CENTER)
            button.setOnClickListener {
                openNote(noteName)
            }

            linearLayout.addView(button)
        }
    }

    fun createNewNote(view: View) {
        openNote("")
    }

    fun openNote(noteName: String) {
        val intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra("NOTE_NAME", noteName)
        startActivity(intent)
    }
}
