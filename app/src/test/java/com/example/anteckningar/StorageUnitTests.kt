package com.example.anteckningar

import android.content.Context
import org.junit.Test
import org.junit.Assert
import org.mockito.Mockito

import com.example.anteckningar.adapters.Storage
import org.junit.Before


class StorageUnitTests {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = Mockito.mock(Context::class.java)
    }

    @Test
    fun storagePath() {
        var storage = Storage(context)
        Assert.assertEquals(context.filesDir, storage.storagePath)
    }
}