package com.example.noponto

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noponto.databinding.ActivityLoginBinding

class LoginActivityWithBinding : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

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
                showError("Por favor, insira seu email")
                false
            }
            password.isEmpty() -> {
                showError("Por favor, insira sua senha")
                false
            }
            !isValidEmail(email) -> {
                showError("Por favor, insira um email válido")
                false
            }
            else -> true
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun performLogin(email: String, password: String) {
        // Implemente sua lógica de autenticação aqui
        showSuccess("Login bem-sucedido!")

        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish() // Impede que o usuário volte para a tela de login ao pressionar o botão "voltar"
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}