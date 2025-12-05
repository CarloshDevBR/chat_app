package com.example.chatapp.data.model.request

data class RegisterUserRequest(
    val id: String = String(),
    val name: String = String(),
    val email: String = String(),
    val photoUrl: String = String(),
    val createdAt: Long = System.currentTimeMillis()
)