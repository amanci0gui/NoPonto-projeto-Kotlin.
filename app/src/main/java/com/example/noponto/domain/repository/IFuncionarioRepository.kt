package com.example.noponto.domain.repository

import com.example.noponto.domain.model.Funcionario

interface IFuncionarioRepository {
    suspend fun registerFuncionario(funcionario: Funcionario, password: String): Result<Unit>

    suspend fun getFuncionarioByEmail(email: String): Result<Funcionario?>

    suspend fun updateFuncionarioStatus(id: String, status: Boolean): Result<Unit>

    suspend fun getAllFuncionarios(): Result<List<Funcionario>>

    suspend fun deleteFuncionario(id: String): Result<Unit>

    suspend fun updateFuncionario(funcionario: Funcionario): Result<Unit>

    suspend fun getFuncionarioById(id: String): Result<Funcionario?>

    suspend fun getFuncionariosByStatus(status: Boolean): Result<List<Funcionario>>

    suspend fun searchFuncionariosByName(name: String): Result<List<Funcionario>>
}