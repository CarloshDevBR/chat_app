package com.example.chatapp.presentation.home.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.chatapp.databinding.SubFragmentChatListBinding
import com.example.chatapp.presentation.home.adapter.ChatAdapter
import com.example.chatapp.presentation.sharedviewmodels.HomeListsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatListFragment : Fragment() {
    private var _binding: SubFragmentChatListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeListsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SubFragmentChatListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupListChat()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                // TODO - remover
                delay(1000)
                swipeRefreshLayout.isRefreshing = IS_REFRESHING
            }
        }
    }

    private fun setupListChat() = with(binding.recyclerChatList) {
        val list = viewModel.getChats()
        adapter = ChatAdapter(list)
    }

    private companion object {
        const val IS_REFRESHING = false
    }
}