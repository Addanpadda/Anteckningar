package com.example.anteckningar.usecases

import android.content.Context


interface Storage_Interface {
    var storagePath: String

    fun saveFile(fileName: String, content: String)
    fun loadFile(fileName: String): String
    fun listFiles(context: Context): Array<String>
}
