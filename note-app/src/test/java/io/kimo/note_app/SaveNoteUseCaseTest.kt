package io.kimo.note_app

import org.junit.Assert
import org.junit.Test

class SaveNoteUseCaseTest {

    @Test
    fun execute() {
        val note = "This is a test note"

        val fakeStorage = object : Storage {
            override fun save(uniqueId: String, note: String) {}
            override fun load(uniqueId: String) = note
        }

        val saveNoteUseCase = object : UseCase {
            override fun execute() {}
            override fun cancel() {}
        }

        saveNoteUseCase.execute()

        Assert.assertEquals(fakeStorage.load("some id"), note)
    }

    @Test
    fun cancel() {}
}