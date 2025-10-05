package com.example.trovimobileapp.chat.presentation.chat_list

sealed interface ChatListScreenEvent {

    data class GoToChat(val chatId: Int) : ChatListScreenEvent
}