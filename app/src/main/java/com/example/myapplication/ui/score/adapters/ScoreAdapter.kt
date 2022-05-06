package com.example.myapplication.ui.score.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import timber.log.Timber

@BindingAdapter("score_team_a")
fun setScoreTeamA(view: TextView, value: Int){
    view.apply {
        Timber.i("ScoreAdapter --> setScoreTeamA --> $value")
        text = "$value"
    }
}
