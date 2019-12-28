package io.kimo.note_app

interface Storage {
    fun save(uniqueId: String, note: String)
    fun load(uniqueId: String) : String
}