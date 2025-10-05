package com.example.trovimobileapp.host.presentation.chat_list

import com.example.trovimobileapp.core.domain.UiText
import com.example.trovimobileapp.core.domain.model.Chat

data class HostChatListState(
    val message: UiText? = null,
    val chats: List<Chat> = listOf(),
    val isLoadingChats: Boolean = false
)