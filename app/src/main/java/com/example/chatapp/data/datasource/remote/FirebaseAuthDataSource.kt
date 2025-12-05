package com.example.chatapp.data.datasource.remote

import com.example.chatapp.data.model.response.UserResponse
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthDataSource {
    suspend fun signIn(email: String, password: String): FirebaseUser
    suspend fun signUp(name: String, email: String, password: String): FirebaseUser
    suspend fun signOut(): Boolean
}