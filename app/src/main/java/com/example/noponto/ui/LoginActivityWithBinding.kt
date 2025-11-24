package com.example.noponto.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noponto.databinding.ActivityLoginBinding
import android.util.Patterns
import com.example.noponto.data.repository.FuncionarioRepository
import com.example.noponto.domain.usecase.AuthenticationUseCase
import com.google.firebase.auth.FirebaseAuth

class LoginActivityWithBinding : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()
    private val funcionarioRepository = FuncionarioRepository()
    private val authentication = AuthenticationUseCase(auth, funcionarioRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLoginButton()
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()

        if (validateInputs(email, password)) {
            performLogin(email, password)
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                showMessage("Por favor, insira seu email")
                false
            }
            password.isEmpty() -> {
                showMessage("Por favor, insira sua senha")
                false
            }
            !isValidEmail(email) -> {
                showMessage("Por favor, insira um email válido")
                false
            }
            else -> true
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun performLogin(email: String, password: String) {
        authentication.signIn(email, password, { success, user, error ->
            if (success) {
                showMessage("Login bem-sucedido!")
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showMessage("Falha no login: $error")
            }
        })
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}