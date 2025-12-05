package com.example.chatapp.domain.usecase.auth

import com.example.chatapp.data.datasource.remote.FirebaseAuthDataSource
import com.example.chatapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

interface SignOutUseCase {
    suspend operator fun invoke(): Flow<Boolean>
}

class SignOutUseCaseImpl(
    private val repository: AuthRepository
) : SignOutUseCase {
    override suspend fun invoke(): Flow<Boolean> {
        return repository.signOut()
    }
}