package com.example.noponto

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.noponto.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // **Step 1: Go edge-to-edge**
        // This allows the app to draw under the system bars.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define a mensagem de boas-vindas e o nome do usuário na barra
        displayWelcomeMessage()

        // Configura os cliques nos ícones da barra
        setupClickListeners()
    }

    private fun displayWelcomeMessage() {
        // Simulação de dados do usuário
        val userRole = "ADMINISTRADOR"
        val userName = "Pedro Franca"

        binding.welcomeMessageTextView.text = "BEM VINDO,\n$userRole!"
        // O ID do TextView do nome do usuário em app_bar.xml é userRole
        binding.appBarLayout.userRole.text = userName
    }

    private fun setupClickListeners() {
        // Listener para o ícone de menu
        binding.appBarLayout.menuIcon.setOnClickListener {
            val menuFragment = MenuDialogFragment()
            menuFragment.show(supportFragmentManager, "MenuDialogFragment")
        }

        // Listener para o ícone de notificação
        binding.appBarLayout.notificationIcon.setOnClickListener {
            Toast.makeText(this, "Notificações Clicado!", Toast.LENGTH_SHORT).show()
        }

        // Listener para a área do usuário
        binding.appBarLayout.userLayout.setOnClickListener {
            Toast.makeText(this, "Perfil do Usuário Clicado!", Toast.LENGTH_SHORT).show()
        }
    }
}
