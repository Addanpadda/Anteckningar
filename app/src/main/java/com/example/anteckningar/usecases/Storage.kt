package com.example.anteckningar.usecases


interface Storage {
    var storagePath: String

    fun saveFile(fileName: String, content: String)
    fun deleteFile(fileName: String)
    fun loadFile(fileName: String): String
    fun listFiles(): Array<String>
}
