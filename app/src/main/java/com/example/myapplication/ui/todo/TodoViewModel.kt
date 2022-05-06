package com.example.myapplication.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.todo.model.TodoItem

class TodoViewModel : ViewModel() {
    private val _todoList = MutableLiveData<MutableList<TodoItem>>(mutableListOf())
    val todoList: LiveData<MutableList<TodoItem>> get() = _todoList

    init {
        for (i in 1..5) _todoList.value?.add(TodoItem("todo$i"))
    }

}