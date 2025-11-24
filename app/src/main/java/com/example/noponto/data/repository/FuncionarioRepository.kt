package com.example.noponto.data.repository

import com.example.noponto.domain.model.Funcionario
import com.example.noponto.domain.repository.IFuncionarioRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FuncionarioRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : IFuncionarioRepository {
    override suspend fun registerFuncionario(funcionario: Funcionario, password: String): Result<Unit> {
        return try {
            val user = auth.createUserWithEmailAndPassword(funcionario.email, password).await()

            val uid = user.user?.uid ?: return Result.failure(Exception("Failed to get user ID"))

            val toSave = funcionario.copy(id = uid)
            firestore.collection("funcionarios").document(uid)
                .set(toSave)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFuncionarioByEmail(email: String): Result<Funcionario?> {
        return try {
            val querySnapshot = firestore.collection("funcionarios")
                .whereEqualTo("email", email)
                .get()
                .await()

            val funcionario = querySnapshot.documents.firstOrNull()?.toObject(Funcionario::class.java)
            Result.success(funcionario)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateFuncionarioStatus(
        id: String,
        status: Boolean
    ): Result<Unit> {
        return try {
            firestore.collection("funcionarios").document(id)
                .update("status", status)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllFuncionarios(): Result<List<Funcionario>> {
        return try {
            val querySnapshot = firestore.collection("funcionarios")
                .get()
                .await()

            val funcionarios = querySnapshot.documents.map() { it.toObject(Funcionario::class.java)!! }

            Result.success(funcionarios)
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    override suspend fun deleteFuncionario(id: String): Result<Unit> {
        return try {
            firestore.collection("funcionarios").document(id)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateFuncionario(funcionario: Funcionario): Result<Unit> {
        return try {
            firestore.collection("funcionarios").document(funcionario.id)
                .set(funcionario)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFuncionarioById(id: String): Result<Funcionario?> {
        return try {
            val documentSnapshot = firestore.collection("funcionarios")
                .document(id)
                .get()
                .await()

            val funcionario = documentSnapshot.toObject(Funcionario::class.java)
            Result.success(funcionario)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFuncionariosByStatus(status: Boolean): Result<List<Funcionario>> {
        return try {
            val querySnapshot = firestore.collection("funcionarios")
                .whereEqualTo("status", status)
                .get()
                .await()

            val funcionarios = querySnapshot.documents.mapNotNull { it.toObject(Funcionario::class.java) }
            Result.success(funcionarios)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchFuncionariosByName(name: String): Result<List<Funcionario>> {
        return try {
            val querySnapshot = firestore.collection("funcionarios")
                .whereGreaterThanOrEqualTo("nome", name)
                .whereLessThanOrEqualTo("nome", name + '\uf8ff')
                .get()
                .await()

            val funcionarios = querySnapshot.documents.mapNotNull { it.toObject(Funcionario::class.java) }
            Result.success(funcionarios)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}