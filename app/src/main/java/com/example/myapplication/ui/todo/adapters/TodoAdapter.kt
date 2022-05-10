package com.example.myapplication.ui.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTodoBinding
import com.example.myapplication.ui.todo.model.TodoItem

class RecyclerViewLifecycleRegistry(
    owner: LifecycleOwner,
    private val parent: Lifecycle
) : LifecycleRegistry(owner) {

    private val parentLifecycleObserver by lazy {
        object: LifecycleObserver {
            @OnLifecycleEvent(Event.ON_ANY)
            fun onAny() {
                currentState = parent.currentState
            }
        }
    }

    var highestState = State.INITIALIZED
        set(value) {
            field = value
            if (parent.currentState >= value) {
                currentState = value
            }
        }

    init {
        observeParent()
    }

    private fun observeParent() {
        parent.addObserver(parentLifecycleObserver)
    }

    private fun ignoreParent() {
        parent.removeObserver(parentLifecycleObserver)
    }

    override fun setCurrentState(nextState: State) {
        val maxNextState = if (nextState > highestState)
            highestState else nextState
        if (nextState == State.DESTROYED) {
            ignoreParent()
        }
        super.setCurrentState(maxNextState)
    }
}

class TodoAdapter(
//    private val parentLifeCycleOwner: LifecycleOwner,
    private val todoList: MutableList<TodoItem>,
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

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
//
//    override fun onViewAttachedToWindow(holder: TodoViewHolder) {
//        super.onViewAttachedToWindow(holder)
//        holder.lifecycle.highestState = Lifecycle.State.RESUMED
//    }
//
//    override fun onViewDetachedFromWindow(holder: TodoViewHolder) {
//        holder.lifecycle.highestState = Lifecycle.State.CREATED
//        super.onViewDetachedFromWindow(holder)
//    }

    class TodoViewHolder(
        private val binding: ItemTodoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoItem) {
            binding.todoItem = todo
            binding.executePendingBindings()
        }
    }

    fun updateTodoList(todo: MutableList<TodoItem>) {
        todoList.clear()
        todoList.addAll(todo)
        notifyDataSetChanged()
    }
}