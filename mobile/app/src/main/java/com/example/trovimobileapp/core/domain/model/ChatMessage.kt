package com.example.trovimobileapp.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val id: Int,
    val content: String,
    val isHostMessage: Boolean,
    val timestamp: String,
    val chatId: Int,
    val type: String,
    val status: String,
)
