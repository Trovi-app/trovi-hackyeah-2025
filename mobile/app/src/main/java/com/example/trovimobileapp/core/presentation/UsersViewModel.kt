package com.example.trovimobileapp.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trovimobileapp.core.data.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel(
    private val usersRepository: UsersRepository
): ViewModel() {

    private val _usersState = MutableStateFlow(UsersState())
    val usersState = _usersState.asStateFlow()

    init {
//        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            val users = usersRepository.getUsers()
            _usersState.update {
                it.copy(
                    users = users
                )
            }
        }
    }

    fun createUser() {
        viewModelScope.launch {
            usersRepository.createUser()
        }
    }
}