package com.example.myapplication.ui.score.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.ui.score.ScoreViewModel

class ResetDialogConfirmFragment : DialogFragment() {
    private val viewModel: ScoreViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder
                .setMessage(getString(R.string.resetDialog))
                .setPositiveButton(getString(R.string.resetDialogConfirmReset)){ dialog, id ->
                    viewModel.scoreReset()
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.resetDialogCancelReset)){ dialog, id ->
                    dialog.cancel()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}