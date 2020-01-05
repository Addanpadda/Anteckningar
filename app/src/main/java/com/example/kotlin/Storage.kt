package com.example.kotlin

import android.content.Context;


interface Storage_Interface {
    var storagePath: String

    fun saveFile(fileName: String, content: String)
    fun loadFile(fileName: String) : String
    fun listFiles() : Array<String>
}


class Storage: Storage_Interface {
    override var storagePath = "files"

    override fun saveFile(fileName: String, content: String) {

    }

    override fun loadFile(fileName: String): String {

    }

    fun listFiles(context: Context) : Array<String> {
        return context.fileList()
    }
}