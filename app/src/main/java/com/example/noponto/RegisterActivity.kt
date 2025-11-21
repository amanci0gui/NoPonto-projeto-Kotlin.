package com.example.noponto

import android.content.Intent
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

        binding.btnRegistrarPonto.setOnClickListener {
            val intent = Intent(this, ClockInActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegistrarOcorrencia.setOnClickListener {
            val intent = Intent(this, OccurrenceActivity::class.java)
            startActivity(intent)
        }

        binding.btnCadastrarPlano.setOnClickListener {
            val intent = Intent(this, PlanRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}