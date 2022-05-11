package com.example.myapplication.ui.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTodoBinding
import com.example.myapplication.ui.todo.model.TodoItem

class TodoAdapter(
//    private val parentLifeCycleOwner: LifecycleOwner,
    private var todoList: ArrayList<TodoItem>,
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private lateinit var _binding: ItemTodoBinding
    private val binding get() = _binding

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TodoViewHolder {
        _binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TodoViewHolder, position: Int
    ) {
        val todoItem = todoList[position]
        holder.bind(todoItem)
    }

    override fun getItemCount(): Int = todoList.size

    class TodoViewHolder(
        private val binding: ItemTodoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoItem) {
            binding.todoItem = todo
//            binding.executePendingBindings()
        }
    }

    fun updateTodoList(todo: List<TodoItem>) {
        todoList = ArrayList(todo)
        notifyDataSetChanged()
    }
}

class TodoItemDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }

}