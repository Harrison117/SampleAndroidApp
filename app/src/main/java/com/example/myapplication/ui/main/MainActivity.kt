package com.example.myapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.score.ScoreActivity
import com.example.myapplication.ui.todo.TodoActivity
import com.example.myapplication.ui.todo.model.TodoItem
import com.example.myapplication.ui.wordbank.WordBankActivity
import timber.log.Timber

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivity"

    private val viewModel: MainViewModel by viewModels()
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.leftButton.setOnClickListener(this)
        binding.centerButton.setOnClickListener(this)
        binding.rightButton.setOnClickListener(this)

        binding.spinner.adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            viewModel.list
        )

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, pos: Int, id: Long)
            {
                when (parent?.getItemAtPosition(pos)
                    .toString()
                    .lowercase()) {
                    "left" -> {
                        binding.leftButton.visibility = View.VISIBLE
                        binding.centerButton.visibility = View.INVISIBLE
                        binding.rightButton.visibility = View.INVISIBLE
                    }
                    "right" -> {
                        binding.leftButton.visibility = View.INVISIBLE
                        binding.centerButton.visibility = View.INVISIBLE
                        binding.rightButton.visibility = View.VISIBLE
                    }
                    "center" -> {
                        binding.leftButton.visibility = View.INVISIBLE
                        binding.centerButton.visibility = View.VISIBLE
                        binding.rightButton.visibility = View.INVISIBLE
                    }
                    "all" -> {
                        binding.leftButton.visibility = View.VISIBLE
                        binding.centerButton.visibility = View.VISIBLE
                        binding.rightButton.visibility = View.VISIBLE
                    }
                    "none" -> {
                        binding.leftButton.visibility = View.INVISIBLE
                        binding.centerButton.visibility = View.INVISIBLE
                        binding.rightButton.visibility = View.INVISIBLE
                    }
                    else -> return
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        Timber.tag(TAG).i("onCreate() is called")
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(TAG).i("onStart() is called")
    }

    override fun onResume() {
        super.onResume()

        Timber.tag(TAG).i("onResume() is called")
    }

    override fun onPause() {
        super.onPause()

        Timber.tag(TAG).i("onPause() is called")
    }

    override fun onStop() {
        super.onStop()

        Timber.tag(TAG).i("onStop() is called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.tag(TAG).i("onDestroy() is called")
    }

    override fun onClick(p0: View?) {
        Timber.tag(TAG).i(p0?.id.toString())

        Timber.tag(TAG).i("In!")
        when(p0?.id) {
            binding.leftButton.id -> {
                startActivity(Intent(this, TodoActivity::class.java))
            }

            binding.centerButton.id -> {
                startActivity(Intent(this, ScoreActivity::class.java))
            }

            binding.rightButton.id -> {
                startActivity(Intent(this, WordBankActivity::class.java))
            }

            else -> return
        }
    }
}