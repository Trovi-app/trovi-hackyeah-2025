package com.example.trovimobileapp.chat.di

import com.example.trovimobileapp.chat.data.repository.ChatsRepository
import com.example.trovimobileapp.chat.presentation.chat.ChatViewModel
import com.example.trovimobileapp.chat.presentation.chat_list.ChatListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatModule = module {
    viewModelOf(::ChatListViewModel)
    singleOf(::ChatsRepository)
    viewModel { (chatId: Int, userId: Int) -> ChatViewModel(chatId, userId, chatsRepository = get()) }
}