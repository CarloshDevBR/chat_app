package com.example.chatapp.presentation.home.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chatapp.presentation.home.subfragments.ChatListFragment

class TabsHomeAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = MAX_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            POSITION_CONVERSATION -> ChatListFragment()
            POSITION_CONTACTS -> ChatListFragment()
            else -> ChatListFragment()
        }
    }

    private companion object {
        const val MAX_TABS = 2
        const val POSITION_CONVERSATION = 0
        const val POSITION_CONTACTS = 1
    }
}