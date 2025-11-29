package com.example.chatapp.data.model.response

data class UserResponse(
    val id: String = String(),
    val name: String = String(),
    val email: String = String(),
    val password: String = String(),
    val image: String = String()
)