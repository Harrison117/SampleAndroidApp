package com.example.myapplication.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel: ViewModel() {
    private val _scoreTeamA: MutableLiveData<Int> = MutableLiveData<Int>(3)
    val scoreTeamA: LiveData<Int> get() = _scoreTeamA

    private val _scoreTeamB: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val scoreTeamB: LiveData<Int> get() = _scoreTeamB

    val string123: MutableLiveData<String> = MutableLiveData("string")

    fun teamAPlus(points: Int) {
        _scoreTeamA.value = (_scoreTeamA.value)?.plus(points)
    }
    fun teamBPlus(points: Int) {
        _scoreTeamB.value = (_scoreTeamB.value)?.plus(points)
    }
    fun scoreReset() {
        _scoreTeamA.value = 0
        _scoreTeamB.value = 0
    }
}