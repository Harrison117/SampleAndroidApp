package com.example.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this

        binding.textView.text = getString(R.string.hello)

        binding.button.setOnClickListener {
            // starts up intent
            startActivity(Intent(this, MainActivity::class.java))

            // finishes the current activity
            // this prevents the previous activity from going back here
            finish()
        }
    }
}