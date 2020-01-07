package com.example.anteckningar.usecases

import android.content.Context


interface Storage {
    var storagePath: String

    fun saveFile(fileName: String, content: String)
    fun loadFile(fileName: String): String
    fun listFiles(context: Context): Array<String>
}
