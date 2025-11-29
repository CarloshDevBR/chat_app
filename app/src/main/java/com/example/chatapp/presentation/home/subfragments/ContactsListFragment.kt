package com.example.chatapp.presentation.home.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.chatapp.data.model.response.ContactsResponse
import com.example.chatapp.databinding.SubFragmentContactsListBinding
import com.example.chatapp.presentation.home.HomeViewModel
import com.example.chatapp.presentation.home.adapter.ContactsAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactsListFragment : Fragment() {
    private var _binding: SubFragmentContactsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SubFragmentContactsListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupListeners()
        viewModel.getContacts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObserver() {
        viewModel.contacts.observe(viewLifecycleOwner) { chats ->
            setupListChat(chats)
        }
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

    private fun setupListChat(items: List<ContactsResponse>) = with(binding.recyclerContactsList) {
        adapter = ContactsAdapter(items)
    }

    private companion object {
        const val IS_REFRESHING = false
    }
}