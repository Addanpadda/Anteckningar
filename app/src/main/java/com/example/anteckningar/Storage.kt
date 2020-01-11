package com.example.anteckningar

import com.example.anteckningar.entities.Note
import com.example.anteckningar.usecases.Storage
import java.io.*


class Storage : Storage {
    override var storagePath = String()

    constructor(path: String) {
        // TODO: Seperate files dir for the files
        // storagePath = context.filesDir+"/files"

        storagePath = path //context.filesDir as String
    }

    override fun saveNote(note: Note) {
        var file = File(storagePath, note.name)
        if (!file.exists()) {
            file.createNewFile()
        }

        var writer = BufferedWriter(FileWriter(file, false))
        writer.write(note.content)
        writer.close()
    }

    override fun deleteNote(note: Note) {
        var file = File(storagePath+"/"+note.name)
        if (file.exists()) file.delete()
    }

    override fun loadNote(fileName: String): Note {
        if (fileName.isEmpty() || !File(storagePath, fileName).exists()) {
            return Note(String(), String())
        }

        var reader = BufferedReader(FileReader(storagePath + "/" + fileName))
        var content = reader.readText()
        reader.close()

        return Note(fileName, content)
    }

    override fun listNoteNames(): Array<String> {
        var fileNames: MutableList<String> = ArrayList()
        var files = File(storagePath).listFiles()

        for (file in files) {
            fileNames.add(file.name)
        }

        return fileNames.toTypedArray()
    }
}