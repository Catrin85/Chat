package com.katesin.testchat.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.katesin.testchat.R
import com.katesin.testchat.cache.ChatDatabase
import com.katesin.testchat.domain.messages.MessageEntity
import com.katesin.testchat.presentation.viewmodel.MessagesViewModel
import com.katesin.testchat.ui.App
import com.katesin.testchat.ui.core.BaseListFragment
import com.katesin.testchat.ui.core.ext.onFailure
import com.katesin.testchat.ui.core.ext.onSuccess


class ChatsFragment : BaseListFragment() {

    override val viewAdapter = ChatsAdapter()

    override val titleToolbar = R.string.chats

    private lateinit var messagesViewModel: MessagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messagesViewModel = viewModel {
            onSuccess(progressData, ::updateProgress)
            onFailure(failureData, ::handleFailure)
        }

        viewAdapter.setOnClick( { it, v ->
            (it as? MessageEntity)?.let {
                val contact = it.contact
                if (contact != null) {
                    navigator.showChatWithContact(contact.id, contact.name, requireActivity())
                }
            }
        })

        ChatDatabase.getInstance(requireContext()).messagesDao.getLiveChats().observe(this, Observer {
            val list = it.distinctBy { it.contact?.id }.toList()
            handleChats(list)
        })
    }

    override fun onResume() {
        super.onResume()

        messagesViewModel.getChats()
    }

    fun handleChats(messages: List<MessageEntity>?) {
        if (messages != null && messages.isNotEmpty()) {
            viewAdapter.submitList(messages)
        }
    }
}
