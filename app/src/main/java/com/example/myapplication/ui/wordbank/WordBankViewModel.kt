package com.example.myapplication.ui.wordbank

import androidx.lifecycle.*
import com.example.myapplication.db.entities.WordEntity
import com.example.myapplication.db.repos.WordRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordBankViewModel(private val repository: WordRepository): ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<WordEntity>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: WordEntity) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordBankViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordBankViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordBankViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}