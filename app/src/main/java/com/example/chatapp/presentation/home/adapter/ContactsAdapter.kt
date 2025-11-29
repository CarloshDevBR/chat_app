package com.example.chatapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.data.model.response.ContactsResponse
import com.example.chatapp.databinding.ItemContactsBinding
import com.example.chatapp.presentation.home.holder.ContactsViewHolder

class ContactsAdapter(
    private val items: List<ContactsResponse>
) : RecyclerView.Adapter<ContactsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsViewHolder {
        val binding = ItemContactsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}