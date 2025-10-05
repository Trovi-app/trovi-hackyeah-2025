package com.example.trovimobileapp.host.presentation.chat

import com.example.trovimobileapp.core.domain.UiText
import com.example.trovimobileapp.core.domain.model.Chat

data class HostChatState(
    val message: UiText? = null,
    val chat: Chat? = null,
    val isLoadingChat: Boolean = false,
    val typedMessage: String = "",
    val cost: String = "10",
)