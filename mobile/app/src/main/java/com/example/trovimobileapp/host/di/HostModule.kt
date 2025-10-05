package com.example.trovimobileapp.host.di

import com.example.trovimobileapp.host.presentation.chat.HostChatViewModel
import com.example.trovimobileapp.host.presentation.chat_list.HostChatListViewModel
import com.example.trovimobileapp.host.presentation.profile_edit.HostProfileEditViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val hostModule = module {
    viewModelOf(::HostChatViewModel)
    viewModelOf(::HostChatListViewModel)
    viewModel { (hostId: Int) -> HostProfileEditViewModel(hostId, usersRepository = get()) }
}