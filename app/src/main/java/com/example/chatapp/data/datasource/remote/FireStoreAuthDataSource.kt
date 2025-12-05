package com.example.chatapp.data.datasource.remote

import com.example.chatapp.data.model.request.RegisterUserRequest

interface FireStoreAuthDataSource {
    suspend fun saveUser(user: RegisterUserRequest)
}