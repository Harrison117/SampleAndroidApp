package com.example.myapplication

import android.app.Application
import com.example.myapplication.db.WordRoomDatabase
import com.example.myapplication.db.repos.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SampleApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())


    val wordDatabase by lazy {
        WordRoomDatabase.getDatabase(this, applicationScope)
    }
    val wordRepository by lazy {
        WordRepository(wordDatabase.wordDao())
    }
}