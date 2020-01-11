package com.example.anteckningar

import com.example.anteckningar.entities.Note
import org.junit.Test
import org.junit.Assert
import com.example.anteckningar.usecases.Storage


class StorageTestImplement : Storage {
    override var storagePath = "/data/data/com.example.anteckningar"
    var files: HashMap<String, String> = hashMapOf()


    override fun saveNote(note: Note) {
        files.put(note.name, note.content)
    }

    override fun deleteNote(note: Note) {
        files.remove(note.name)
    }

    override fun loadNote(fileName: String): Note {
        return Note("", files.getValue(fileName))
    }

    override fun listNoteNames(): Array<String> {
        return files.keys.toTypedArray()
    }
}


class StorageUnitTests {
    @Test
    fun storageSaveNote() {
        var note = Note("Note-name", "Contents of note.")

        val storage = StorageTestImplement()
        storage.saveNote(note)

        Assert.assertEquals(note.name, storage.files.keys.toTypedArray()[0])
        Assert.assertEquals(note.content, storage.files.values.toTypedArray()[0])
    }

    @Test
    fun storageDeleteFile() {
        val firstNote = Note("Notename1", "")
        val secondNote = Note("Notename2", "")
        var storage = StorageTestImplement()

        storage.saveNote(firstNote)
        storage.saveNote(secondNote)

        Assert.assertEquals(2, storage.listNoteNames().size)
        storage.deleteNote(secondNote)
        Assert.assertEquals(1, storage.listNoteNames().size)
    }

    @Test
    fun storageLoadNote() {
        val note = Note("Notename", "Example content.")
        var storage = StorageTestImplement()

        storage.saveNote(note)
        Assert.assertEquals(note.content, storage.loadNote(note.name).content)
    }

    @Test
    fun storageListFile() {
        val note = Note("Notename", "")
        var storage = StorageTestImplement()

        storage.saveNote(note)
        Assert.assertEquals(note.name, storage.listNoteNames()[0])
    }
}
