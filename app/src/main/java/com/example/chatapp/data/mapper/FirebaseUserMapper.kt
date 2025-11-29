package com.example.chatapp.data.mapper

import com.example.chatapp.data.model.response.UserResponse
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toMapperSignInUserResponse(
    password: String
) = UserResponse(
    id = this.uid,
    name = this.displayName.orEmpty(),
    email = this.email.orEmpty(),
    password = password,
    image = String()
)

fun FirebaseUser.toMapperSignUpUserResponse(
    name: String,
    password: String
) = UserResponse(
    id = this.uid,
    name = this.displayName ?: name,
    email = this.email.orEmpty(),
    password = password,
    image = String()
)