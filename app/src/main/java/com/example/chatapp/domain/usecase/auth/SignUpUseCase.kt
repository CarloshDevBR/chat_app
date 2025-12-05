package com.example.chatapp.domain.usecase.auth

import com.example.chatapp.data.model.response.UserResponse
import com.example.chatapp.data.datasource.remote.FirebaseAuthDataSource
import com.example.chatapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

interface SignUpUseCase {
    suspend operator fun invoke(params: Params): Flow<UserResponse>

    data class Params(val name: String, val email: String, val password: String)
}

class SignUpUseCaseImpl(
    private val repository: AuthRepository,
) : SignUpUseCase {
    override suspend fun invoke(params: SignUpUseCase.Params): Flow<UserResponse> {
        return repository.signUp(params.name, params.email, params.password)
    }
}