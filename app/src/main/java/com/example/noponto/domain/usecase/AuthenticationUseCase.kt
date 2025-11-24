package com.example.noponto.domain.usecase

import com.example.noponto.data.repository.FuncionarioRepository
import com.example.noponto.domain.model.Funcionario
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class AuthenticationUseCase(
    private val auth: FirebaseAuth = Firebase.auth,
    private val funcionarioRepository: FuncionarioRepository
) {
    suspend fun signUp(funcionario: Funcionario, password: String) {
        funcionarioRepository.registerFuncionario(funcionario, password)
    }

    fun signIn(email: String, password: String, callback: (success: Boolean, user: FirebaseUser?, error: String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) callback(true, auth.currentUser, null)
                else callback(false, null, task.exception?.localizedMessage)
            }
    }

    fun signOut() { auth.signOut() }

    fun currentUser(): FirebaseUser? = auth.currentUser

    fun sendPasswordReset(email: String, callback: (success: Boolean, error: String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) callback(true, null)
                else callback(false, task.exception?.localizedMessage)
            }
    }
}