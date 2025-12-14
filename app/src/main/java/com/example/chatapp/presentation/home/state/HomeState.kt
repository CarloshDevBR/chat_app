package com.example.chatapp.presentation.home.state

import com.example.chatapp.data.model.response.ChatResponse
import com.example.chatapp.data.model.response.ContactsResponse

sealed interface HomeState {
    data class Tabs(val tabs: Map<Int, String>) : HomeState
    data class Chats(val list: List<ChatResponse>) : HomeState
    data class Contacts(val list: List<ContactsResponse>) : HomeState
    data object LoggedOut : HomeState
}