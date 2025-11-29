package com.example.chatapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.data.model.response.ChatResponse
import com.example.chatapp.databinding.ItemChatBinding
import com.example.chatapp.presentation.home.holder.ChatViewHolder

class ChatAdapter(
    private val items: List<ChatResponse>
) : RecyclerView.Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        val binding = ItemChatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}