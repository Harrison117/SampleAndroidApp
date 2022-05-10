package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.db.daos.TodoDao
import com.example.myapplication.db.entities.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}