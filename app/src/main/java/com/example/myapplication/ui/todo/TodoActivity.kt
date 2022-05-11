package com.example.myapplication.ui.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityTodoBinding
import com.example.myapplication.ui.todo.adapters.TodoAdapter
import com.example.myapplication.ui.todo.model.TodoItem
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class TodoActivity : AppCompatActivity() {
    private val TAG = "TodoActivity"

    private val viewModel: TodoViewModel by viewModels()
    private lateinit var _binding: ActivityTodoBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_todo)
        binding.lifecycleOwner = this

        binding.addTodoButton.setOnClickListener {
            if (binding.addTodoTextBox.text.isNotBlank() or
                binding.addTodoTextBox.text.isNotEmpty()
            ) {
                val newTodo = TodoItem(
                    id = Random.nextInt(),
                    description = binding.addTodoTextBox.text.toString(),
                    author = "curr",
                    // TODO reformat date provider
                    date = SimpleDateFormat("EEE, MMM d").format(Date())
                )
                viewModel.addTodo(newTodo)
                binding.addTodoTextBox.text.clear()
            } else {
                // TODO: Snackbar("ENTER A TODO ITEM")
            }
        }

        val adapter = TodoAdapter(viewModel.todoListLiveData.value as ArrayList)
        binding.todoRecyclerView.adapter = adapter
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.todoRecyclerView.setHasFixedSize(true)

        Observer<List<TodoItem>> { t ->
            if (t != null) {
                Timber.tag(TAG).i(t.toString())
                adapter.updateTodoList(t)
            }
        }.also {
            viewModel.todoListLiveData.observe(this, it)
        }
    }

    override fun onDestroy() {
        binding.todoRecyclerView.adapter = null
        super.onDestroy()
    }
}