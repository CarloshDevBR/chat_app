package com.example.chatapp.data.repository

import com.example.chatapp.data.datasource.remote.FirebaseAuthDataSource
import com.example.chatapp.data.mapper.toMapperUserResponse
import com.example.chatapp.data.model.response.UserResponse
import com.example.chatapp.domain.errors.AuthError
import com.example.chatapp.domain.repository.AuthRepository
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    val firebase: FirebaseAuthDataSource
) : AuthRepository {
    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<UserResponse> = flow {
        try {
            val user = firebase.signIn(email = email, password = password)
            emit(user.toMapperUserResponse())
        } catch (e: FirebaseException) {
            throw handlerError(e)
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Flow<UserResponse> = flow {
        try {
            val user = firebase.signUp(name = name, email = email, password = password)
            emit(user.toMapperUserResponse(name = name))
        } catch (e: FirebaseException) {
            throw handlerError(e)
        }
    }

    override suspend fun signOut(): Flow<Boolean> = flow {
        try {
            emit(firebase.signOut())
        } catch (e: FirebaseException) {
            throw e
        }
    }

    private fun handlerError(exception: FirebaseException) = when (exception) {
        is FirebaseAuthUserCollisionException -> AuthError.EmailAlreadyInUse
        is FirebaseAuthInvalidCredentialsException -> AuthError.InvalidCredentials
        is FirebaseNetworkException -> AuthError.NetworkError
        else -> AuthError.UnknownError
    }
}