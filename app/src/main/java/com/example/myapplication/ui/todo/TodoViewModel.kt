package com.example.myapplication.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.todo.model.TodoItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TodoViewModel : ViewModel() {
    private val todoList = ArrayList<TodoItem>()
    private val _todoListLiveData = MutableLiveData<List<TodoItem>>()
    val todoListLiveData: LiveData<List<TodoItem>> get() = _todoListLiveData

    init {
        initData()
    }

    private fun initData() {
        for(i in 1..5) addTodo(
            TodoItem(
                id = i,
                description = "todo$i",
                author = "USER",
                date = SimpleDateFormat("EEE, MMM d").format(Date())
            )
        )
    }

    fun addTodo(todo: TodoItem) {
        // adds item to a separate list
        todoList.add(todo)

        // updates the LiveData value, triggering update event
        _todoListLiveData.value = todoList
    }

}