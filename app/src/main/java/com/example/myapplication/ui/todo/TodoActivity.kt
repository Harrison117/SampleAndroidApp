package com.example.myapplication.ui.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityTodoBinding
import com.example.myapplication.ui.main.MainViewModel
import com.example.myapplication.ui.todo.adapters.TodoAdapter
import com.example.myapplication.ui.todo.model.TodoItem

class TodoActivity : AppCompatActivity() {
    private val TAG = "TodoActivity"

    private val viewModel: TodoViewModel by viewModels()
    private lateinit var _binding: ActivityTodoBinding
    private val binding get() = _binding
    private lateinit var todoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_todo)
        binding.lifecycleOwner = this

        binding.addTodoButton.setOnClickListener {
            if (binding.addTodoTextBox.text.isNotBlank()
                or binding.addTodoTextBox.text.isNotEmpty()) {

                viewModel.todoList.value?.add(
                    TodoItem(binding.addTodoTextBox.text.toString()))
            }
        }

        todoRecyclerView = binding.todoRecyclerView
        val sampleTodoList = viewModel.todoList

        todoRecyclerView.adapter = sampleTodoList.value?.let { TodoAdapter(it) }
        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        todoRecyclerView.setHasFixedSize(true)


        val todoObserver =
            Observer<MutableList<TodoItem>> { t ->
                if (t != null) {
                    (todoRecyclerView.adapter as TodoAdapter?)?.updateTodoList(t)
                }
            }
    }
}