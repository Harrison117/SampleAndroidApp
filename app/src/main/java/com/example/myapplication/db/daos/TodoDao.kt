package com.example.myapplication.db.daos

import androidx.room.*
import com.example.myapplication.db.entities.TodoEntity

@Dao
interface TodoDao {
    @Insert
    fun addTodo(vararg todo: TodoEntity)

    @Insert
    fun addAllTodos(vararg todos: TodoEntity)

    @Delete
    fun deleteTodo(todo: TodoEntity)

    @Delete
    fun deleteAll()

    @Query("SELECT * FROM todos")
    fun findAll(): List<TodoEntity>

    @Query("SELECT * FROM todos WHERE author LIKE :author")
    fun findByAuthor(author: String): List<TodoEntity>

//    TODO:
//     - implement findByDate DAO function
//     - use Date type, not String
//    @Query("SELECT * FROM todos WHERE author LIKE :date")
//    fun findByDate(date: String): List<TodoEntity>


}