package com.example.myapplication.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    init {

    }

    val list = mutableListOf<String>(
        "All",
        "Left",
        "Right",
        "Center",
        "None"
    )

}