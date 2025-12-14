package com.example.chatapp.presentation.sharedviewmodels

import androidx.lifecycle.ViewModel
import com.example.chatapp.data.model.response.ChatResponse
import com.example.chatapp.data.model.response.ContactsResponse

class HomeListsViewModel : ViewModel() {
    private var chats: List<ChatResponse> = emptyList()
    private var contacts: List<ContactsResponse> = emptyList()

    fun setCharts(list: List<ChatResponse>) {
        chats = list
    }

    fun setContacts(list: List<ContactsResponse>) {
        contacts = list
    }

    fun getChats() = chats

    fun getContacts() = contacts
}