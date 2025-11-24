package com.example.noponto.domain.model

import com.google.firebase.firestore.DocumentId
import java.time.LocalDate

data class Funcionario(
    @DocumentId
    val id: String,
    val nome: String,
    val email: String,
    val cpf: String,
    val status: Boolean,
    val dataNascimento: LocalDate,
    val cargo: Cargo = Cargo.DESENVOLVEDOR,
    val endereco: Endereco
)