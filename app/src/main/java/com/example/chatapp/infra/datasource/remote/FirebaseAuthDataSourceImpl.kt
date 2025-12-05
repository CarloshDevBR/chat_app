package com.example.chatapp.infra.datasource.remote

import com.example.chatapp.data.datasource.remote.FireStoreAuthDataSource
import com.example.chatapp.data.datasource.remote.FirebaseAuthDataSource
import com.example.chatapp.data.mapper.toMapperRegisterUserRequest
import com.example.chatapp.domain.errors.AuthError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val fireStoreAuthDataSource: FireStoreAuthDataSource
) : FirebaseAuthDataSource {
    override suspend fun signIn(
        email: String,
        password: String
    ): FirebaseUser {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user ?: throw AuthError.UnknownError
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): FirebaseUser {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val firebaseUser = result.user ?: throw AuthError.UnknownError

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .build()

        firebaseUser.updateProfile(profileUpdates).await()

        val userModel = firebaseUser.toMapperRegisterUserRequest()

        fireStoreAuthDataSource.saveUser(userModel)

        return firebaseUser
    }

    override suspend fun signOut(): Boolean {
        firebaseAuth.signOut()
        return true
    }
}