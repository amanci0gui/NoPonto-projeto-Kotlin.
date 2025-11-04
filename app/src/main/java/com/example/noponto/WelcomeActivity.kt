package com.example.noponto // Use o seu pacote real

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noponto.databinding.ActivityWelcomeBinding // Nome gerado pelo ViewBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inflar o Layout com View Binding
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Configurar a Toolbar (para mostrar o ícone de menu)
        setupToolbar()

        // 3. Preencher a mensagem central (simulando dados de login)
        displayWelcomeMessage()

        // 4. Configurar interações (simulando clique na área do usuário)
        setupInteractions()
    }

    private fun setupToolbar() {
        // Configura a Toolbar como a ActionBar da Activity
        setSupportActionBar(binding.appBarLayout.toolbar)

        // Ativa o ícone do menu hamburguer (Ícone padrão do Android)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // Substitua por seu ícone de menu
        supportActionBar?.setDisplayShowTitleEnabled(false) // Oculta o título padrão da Activity
    }

    private fun displayWelcomeMessage() {
        // Simulação de dados do usuário logado
        val userName = "Nome do Usuário"
        val userRole = "Gerente de Turno" // Exemplo: Cargo da Pessoa

        // Monta a mensagem central
        val welcomeText = "BEM VINDO,\n${userRole.uppercase()}!"

        binding.welcomeMessageTextView.text = welcomeText
        binding.userNameTextView.text = userName
    }

    private fun setupInteractions() {
        // Clique no ícone do menu (Abre o Drawer, se implementado)
        binding.appBarLayout.toolbar.setNavigationOnClickListener {
            // Se você tiver um DrawerLayout, você o abriria aqui:
            // binding.drawerLayout.openDrawer(GravityCompat.START)
            Toast.makeText(this, "Menu Clicado!", Toast.LENGTH_SHORT).show()
        }

        // Clique na área de notificação
        binding.appBarLayout.notificationIcon.setOnClickListener {
            Toast.makeText(this, "Notificações!", Toast.LENGTH_SHORT).show()
        }

        // Clique na área do usuário
        binding.appBarLayout.userLayout.setOnClickListener {
            Toast.makeText(this, "Perfil do Usuário Clicado!", Toast.LENGTH_SHORT).show()
        }
    }
}