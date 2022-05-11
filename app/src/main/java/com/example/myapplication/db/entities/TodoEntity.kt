package com.example.myapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "todos",
    indices = [Index(value = ["author", "date_created"])])
data class TodoEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo
        val id: Int,

    @ColumnInfo(name = TODO_DESCRIPTION)
        val description: String,

    @ColumnInfo(name = TODO_AUTHOR)
        val author: String,

    @ColumnInfo(name = TODO_DATE_CREATED)
        val dateCreated: String,

) {
    companion object {
        const val TODO_DESCRIPTION = "description"
        const val TODO_AUTHOR = "author"
        const val TODO_DATE_CREATED = "date_created"
    }
}