package com.example.chatapp.presentation.signin.state

sealed interface SignInState {
    data object Logged : SignInState
}