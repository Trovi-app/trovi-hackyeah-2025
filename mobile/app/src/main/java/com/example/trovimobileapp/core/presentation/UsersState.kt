package com.example.trovimobileapp.core.presentation

import com.example.trovimobileapp.core.domain.model.User

data class UsersState(
    val users: List<User> = emptyList(),
    val storyText: String? = null,
)
