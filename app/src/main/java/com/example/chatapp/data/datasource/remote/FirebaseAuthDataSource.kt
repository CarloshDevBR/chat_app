package com.example.chatapp.data.datasource.remote

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthDataSource {
    suspend fun signIn(email: String, password: String): FirebaseUser
    suspend fun signUp(name: String, email: String, password: String): FirebaseUser
    suspend fun signOut(): Boolean
}