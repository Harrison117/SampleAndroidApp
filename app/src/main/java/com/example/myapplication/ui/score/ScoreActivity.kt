package com.example.myapplication.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityScoreBinding
import com.example.myapplication.ui.score.fragments.ResetDialogConfirmFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.coroutineContext

class ScoreActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "ScoreActivity"

    private val viewModel: ScoreViewModel by viewModels()
    private lateinit var _binding: ActivityScoreBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_score)
        binding.lifecycleOwner = this

//        binding.teamAScore.text = viewModel.scoreTeamA.toString()
//        binding.teamBScore.text = viewModel.scoreTeamB.toString()

        Observer<Int> { newScore ->
            binding.teamAScore.text = newScore.toString()
        }.also {
            viewModel.scoreTeamA.observe(this, it)
        }

        Observer<Int> { newScore ->
            binding.teamBScore.text = newScore.toString()
        }.also {
            viewModel.scoreTeamB.observe(this, it)
        }

        initUIListeners()


        Timber.tag(TAG).i("onCreate() is called")
    }

    fun coroutinePractice() {
        CoroutineScope(Dispatchers.IO).launch {

        }
    }

    private fun initUIListeners(){
        binding.plus3AButton.setOnClickListener(this)
        binding.plus2AButton.setOnClickListener(this)
        binding.freeThrowAButton.setOnClickListener(this)

        binding.plus3BButton.setOnClickListener(this)
        binding.plus2BButton.setOnClickListener(this)
        binding.freeThrowBButton.setOnClickListener(this)

        binding.resetButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.plus3AButton.id -> {
                viewModel.teamAPlus(3)
            }

            binding.plus2AButton.id -> {
                viewModel.teamAPlus(2)
            }

            binding.freeThrowAButton.id -> {
                viewModel.teamAPlus(1)
            }

            binding.plus3BButton.id -> {
                viewModel.teamBPlus(3)
            }

            binding.plus2BButton.id -> {
                viewModel.teamBPlus(2)
            }

            binding.freeThrowBButton.id -> {
                viewModel.teamBPlus(1)
            }

            binding.resetButton.id -> {
                val reset = ResetDialogConfirmFragment()
                reset.show(supportFragmentManager, "reset")
            }

            else -> return

        }
    }
}