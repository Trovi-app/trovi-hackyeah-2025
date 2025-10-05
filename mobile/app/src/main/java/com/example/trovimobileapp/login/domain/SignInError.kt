package com.example.trovimobileapp.login.domain

import com.example.trovimobileapp.core.domain.Error

sealed interface SignInError: Error {
    data object NetworkError: SignInError
    data object UnknownError: SignInError
}