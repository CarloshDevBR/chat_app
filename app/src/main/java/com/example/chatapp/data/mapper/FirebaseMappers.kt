package com.example.chatapp.data.mapper

import com.example.chatapp.data.model.request.RegisterUserRequest
import com.example.chatapp.data.model.response.UserResponse
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toMapperUserResponse(name: String = String()) = UserResponse(
    id = uid,
    name = displayName ?: name,
    email = email.orEmpty(),
    image = String()
)

fun FirebaseUser.toMapperRegisterUserRequest() = RegisterUserRequest(
    id = uid,
    name = displayName.orEmpty(),
    email = email.orEmpty(),
    photoUrl = photoUrl?.toString().orEmpty()
)