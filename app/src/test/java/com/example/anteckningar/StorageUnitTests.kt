package com.example.anteckningar

import org.junit.Test
import org.junit.Assert
import com.example.anteckningar.usecases.Storage


class StorageTestImplement : Storage {
    override var storagePath = "/data/data/com.example.anteckningar"
    var files: HashMap<String, String> = hashMapOf()


    override fun saveFile(fileName: String, content: String) {
        files.put(fileName, content)
    }

    override fun deleteFile(fileName: String) {
        files.remove(fileName)
    }

    override fun loadFile(fileName: String): String {
        return files.getValue(fileName)
    }

    override fun listFiles(): Array<String> {
        return files.keys.toTypedArray()
    }
}


class StorageUnitTests {
    @Test
    fun storageSaveFile() {
        val fileName = "filename.txt"
        val content = "Example content"

        val storage = StorageTestImplement()
        storage.saveFile(fileName, content)

        Assert.assertEquals(fileName, storage.files.keys.toTypedArray()[0])
        Assert.assertEquals(content, storage.files.values.toTypedArray()[0])
    }

    @Test
    fun storageDeleteFile() {
        val firstFileName = "filename.txt"
        val secondFileName = "filename2.txt"
        var storage = StorageTestImplement()

        storage.saveFile(firstFileName, "")
        storage.saveFile(secondFileName, "")

        Assert.assertEquals(2, storage.listFiles().size)
        storage.deleteFile(secondFileName)
        Assert.assertEquals(1, storage.listFiles().size)
    }

    @Test
    fun storageLoadFile() {
        val content = "Example content"
        val fileName = "filename.txt"
        var storage = StorageTestImplement()

        storage.saveFile(fileName, content)
        Assert.assertEquals(content, storage.loadFile(fileName))
    }

    @Test
    fun storageListFile() {
        val fileName = "filename.txt"
        var storage = StorageTestImplement()

        storage.saveFile(fileName, "")
        Assert.assertEquals(fileName, storage.listFiles()[0])
    }
}
