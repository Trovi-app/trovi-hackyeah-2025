package com.example.trovimobileapp.login.presentation

import com.example.trovimobileapp.R
import com.example.trovimobileapp.core.domain.UiText
import com.example.trovimobileapp.login.domain.SignInError

fun SignInError.asUiText(): UiText {
    return when (this) {
        is SignInError.NetworkError -> {
            UiText.StringResource(R.string.network_error)
        }
        is SignInError.UnknownError -> {
            UiText.StringResource(R.string.unknown_error)
        }
    }
}