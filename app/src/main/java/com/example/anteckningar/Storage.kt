package com.example.anteckningar

import android.content.Context
import com.example.anteckningar.usecases.Storage
import java.io.*


class Storage : Storage {
    override var storagePath = String()
    var context: Context

    constructor(applicationContext: Context) {
        // TODO: Seperate files dir for the files
        // storagePath = context.filesDir+"/files"

        context = applicationContext
        storagePath = context.filesDir as String
    }

    override fun saveFile(fileName: String, content: String) {
        var file = File(storagePath, fileName)
        if (!file.exists()) {
            file.createNewFile()
        }

        var writer = BufferedWriter(FileWriter(file, false))
        writer.write(content)
        writer.close()
    }

    override fun deleteFile(fileName: String) {
        var file = File(storagePath, fileName)
        if (file.exists()) file.delete()
    }

    override fun loadFile(fileName: String): String {
        if (!File(storagePath, fileName).exists()) {
            return String()
        }

        var reader = BufferedReader(FileReader(fileName))
        var content = reader.readText()
        reader.close()

        return content
    }

    override fun listFiles(): Array<String> {
        return context.fileList()
    }
}