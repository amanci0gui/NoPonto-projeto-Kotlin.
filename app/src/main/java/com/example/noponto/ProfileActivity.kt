package com.example.noponto

import android.os.Bundle
import com.example.noponto.databinding.ActivityProfileBinding
import com.example.noponto.databinding.AppBarBinding

class ProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityProfileBinding
    override val appBarBinding: AppBarBinding
        get() = binding.appBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This sets up the app bar automatically
        setupAppBar()

        // You can add logic here to display user profile information
        // For example, you can set the user's name in the app bar
        appBarBinding.userRole.text = "Pedro Perfil"
    }
}
