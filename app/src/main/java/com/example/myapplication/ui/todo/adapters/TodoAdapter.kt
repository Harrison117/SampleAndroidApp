package com.example.myapplication.ui.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTodoBinding
import com.example.myapplication.ui.todo.model.TodoItem

class TodoAdapter(
    private val todoList: MutableList<TodoItem>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(
        private val binding: ItemTodoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoItem) {
            binding.todoItem = todo
        }
    }

    private lateinit var _binding: ItemTodoBinding
    private val binding get() = _binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        _binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoItem = todoList[position]
        holder.bind(todoItem)
    }

    override fun getItemCount(): Int = todoList.size

    fun updateTodoList(todo: MutableList<TodoItem>) {
        todoList.clear()
        todoList.addAll(todo)
        notifyDataSetChanged()
    }
}