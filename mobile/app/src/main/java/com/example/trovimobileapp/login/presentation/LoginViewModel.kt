package com.example.trovimobileapp.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trovimobileapp.core.data.repository.UsersRepository
import kotlinx.coroutines.launch
import com.example.trovimobileapp.core.domain.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class LoginViewModel(
    private val usersRepository: UsersRepository,
) : ViewModel() {

    var loginState by mutableStateOf(LoginState())

    fun onLoginScreenEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.EmailChanged -> {
                loginState = loginState.copy(email = event.email)
            }
            is LoginScreenEvent.PasswordChanged -> {
                loginState = loginState.copy(password = event.password)
            }
            is LoginScreenEvent.SignInWithEmailAndPassword -> {
                loginState = loginState.copy(isLoginPending = true)
                signInWithEmailAndPassword()
            }
            is LoginScreenEvent.ResetState -> {
                loginState = loginState.copy(loginError = null)
            }
        }
    }

    private fun refreshUser() {
        viewModelScope.launch {
            val user = usersRepository.getUserById(loginState.loggedInUser!!.id)
            loginState = loginState.copy(
                loggedInUser = user
            )
        }
    }

    private fun startRefreshingUser() {
        viewModelScope.launch {
            while (isActive) {
                refreshUser()
                delay(1000L)
            }
        }
    }

    private fun signInWithEmailAndPassword() {
        viewModelScope.launch {
            val loginResult = usersRepository.signInWithEmailAndPassword(loginState.email, loginState.password)
            when (loginResult) {
                is Result.Error -> {
                    loginState = loginState.copy(
                        loginError = loginResult.error.asUiText(),
                        isLoginPending = false
                    )
                }
                is Result.Success -> {
                    loginState = loginState.copy(
                        loginError = null,
                        isLoginPending = false,
                        loggedInUser = loginResult.data,
                    )
                }
            }
            startRefreshingUser()
        }
    }

}