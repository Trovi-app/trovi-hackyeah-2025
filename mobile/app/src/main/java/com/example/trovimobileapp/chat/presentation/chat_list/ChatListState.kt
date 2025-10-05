package com.example.trovimobileapp.chat.presentation.chat_list

import com.example.trovimobileapp.core.domain.UiText
import com.example.trovimobileapp.core.domain.model.Chat

data class ChatListState(
    val message: UiText? = null,
    val chats: List<Chat> = listOf(),
    val isLoadingChats: Boolean = false
)