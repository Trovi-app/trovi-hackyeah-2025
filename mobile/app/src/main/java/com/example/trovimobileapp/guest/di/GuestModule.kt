package com.example.trovimobileapp.guest.di

import com.example.trovimobileapp.guest.presentation.home.GuestHomeViewModel
import com.example.trovimobileapp.guest.presentation.host_profile.HostProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val guestModule = module {
    singleOf(::GuestHomeViewModel)
    viewModel { (userId: Int, hostId: Int) -> HostProfileViewModel(userId, hostId, usersRepository = get(), chatsRepository = get()) }
}