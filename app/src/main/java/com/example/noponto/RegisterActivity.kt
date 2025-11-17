package com.example.noponto

import android.os.Bundle
import com.example.noponto.databinding.ActivityRegisterBinding
import com.example.noponto.databinding.AppBarBinding

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override val appBarBinding: AppBarBinding
        get() = binding.appBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This sets up the app bar automatically
        setupAppBar()

        // You can change the name in the app bar for this specific screen
        appBarBinding.userRole.text = "Registro"
    }
}