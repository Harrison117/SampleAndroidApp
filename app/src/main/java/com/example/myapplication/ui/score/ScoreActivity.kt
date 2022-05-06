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
import timber.log.Timber

class ScoreActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "ScoreActivity"

    private val viewModel: ScoreViewModel by viewModels()
    private lateinit var _binding: ActivityScoreBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_score)
        binding.lifecycleOwner = this

        binding.teamAScore.text = viewModel.scoreTeamA.toString()
        binding.teamBScore.text = viewModel.scoreTeamB.toString()

        val teamAScoreObserver = Observer<Int> { newScore ->
            binding.teamAScore.text = newScore.toString()
        }.also {
            viewModel.scoreTeamA.observe(this, it)
        }

//        viewModel.scoreTeamA.observe(this){ newScore ->
//            if (newScore != null)
//            binding.teamAScore.text = newScore.toString()
//            else binding.teamAScore.text = "0"
//        }

        val teamBScoreObserver = Observer<Int> { newScore ->
            binding.teamBScore.text = newScore.toString()
        }.also {
            viewModel.scoreTeamB.observe(this, it)
        }


        initUIListeners()


        Timber.tag(TAG).i("onCreate() is called")
    }

    fun initUIListeners(){
        binding.plus3AButton.setOnClickListener { viewModel.teamAPlus(3) }
        binding.plus2AButton.setOnClickListener { viewModel.teamAPlus(2) }
        binding.freeThrowAButton.setOnClickListener { viewModel.teamAPlus(1) }

        binding.plus3BButton.setOnClickListener { viewModel.teamBPlus(3) }
        binding.plus2BButton.setOnClickListener { viewModel.teamBPlus(2) }
        binding.freeThrowBButton.setOnClickListener { viewModel.teamBPlus(1) }

        binding.resetButton.setOnClickListener {
            val reset = ResetDialogConfirmFragment()
            reset.show(supportFragmentManager, "reset")
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.plus3AButton.id -> viewModel.teamAPlus(3)
        }
    }
}