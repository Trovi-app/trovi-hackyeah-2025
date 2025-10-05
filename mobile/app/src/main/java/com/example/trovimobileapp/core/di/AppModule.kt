package com.example.trovimobileapp.core.di

import com.example.trovimobileapp.core.data.repository.UsersRepository
import com.example.trovimobileapp.core.presentation.UsersViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::UsersViewModel)

    singleOf(::UsersRepository)
}