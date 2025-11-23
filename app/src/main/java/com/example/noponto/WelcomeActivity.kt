package com.example.noponto

import android.os.Bundle
import com.example.noponto.databinding.ActivityWelcomeBinding
import com.example.noponto.databinding.AppBarBinding

class WelcomeActivity : BaseActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override val appBarBinding: AppBarBinding
        get() = binding.appBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This will set up all the app bar logic from the BaseActivity
        setupAppBar()

        // --- Logic specific to WelcomeActivity ---
        displayWelcomeMessage()
    }

    private fun displayWelcomeMessage() {
        val userRole = "ADMINISTRADOR"
        val userName = "Pedro Franca"

        binding.welcomeMessageTextView.text = "BEM VINDO,\n$userRole!"
        appBarBinding.userRole.text = userName
    }
}