package com.example.trovimobileapp.login.presentation

import com.example.trovimobileapp.core.domain.UiText
import com.example.trovimobileapp.core.domain.model.User

data class LoginState(
    val email: String = "traveler@gmail.com",
    val password: String = "dfssiojw",
    val loginError: UiText? = null,
    val isLoginPending: Boolean = false,
    val loggedInUser: User? = null,
)