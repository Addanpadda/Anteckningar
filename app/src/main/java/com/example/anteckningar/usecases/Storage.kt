package com.example.anteckningar.usecases

import com.example.anteckningar.entities.Note


interface Storage {
    var storagePath: String

    fun saveNote(note: Note)
    fun deleteNote(note: Note)
    fun loadNote(noteName: String): Note
    fun listNoteNames(): Array<String>
}
