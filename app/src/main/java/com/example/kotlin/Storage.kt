package com.example.kotlin

import android.content.Context;
import java.io.*


interface Storage_Interface {
    var storagePath: String

    fun saveFile(fileName: String, content: String)
    fun loadFile(fileName: String): String
    fun listFiles(context: Context): Array<String>
}


class Storage: Storage_Interface {
    override var storagePath = String()

    constructor(context: Context) {
        // TODO: Seperate files dir for the files
        // storagePath = context.filesDir+"/files"

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

    override fun loadFile(fileName: String): String {
        var reader = BufferedReader(FileReader(fileName))
        var content = reader.readText()
        reader.close()

        return content
    }

    override fun listFiles(context: Context): Array<String> {
        return context.fileList()
    }
}