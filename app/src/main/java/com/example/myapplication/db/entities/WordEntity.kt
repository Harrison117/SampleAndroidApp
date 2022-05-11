package com.example.myapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "word_table"
)
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
        val id: Int,

    @ColumnInfo(name = WORD_WORDSTRING)
        val word: String,
) {
    companion object {
        const val WORD_WORDSTRING = "word"
    }
}