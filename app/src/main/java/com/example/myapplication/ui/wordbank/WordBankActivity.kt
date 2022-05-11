package com.example.myapplication.ui.wordbank

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.SampleApplication
import com.example.myapplication.db.entities.WordEntity
import com.example.myapplication.ui.wordbank.adapters.WordListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

// TODO: refactor to support data binding
class WordBankActivity : AppCompatActivity() {

    private val wordViewModel: WordBankViewModel by viewModels {
        WordBankViewModelFactory(
            (application as SampleApplication).wordRepository)
    }
    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wordbank)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@WordBankActivity, NewWordActivity::class.java)
            // TODO: refactor deprecated API
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, data: Intent?
    ) {
        // TODO: refactor deprecated API
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode &&
            resultCode == Activity.RESULT_OK
        ) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                val word = WordEntity(
                    id = Random.nextInt(),
                    word = it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}