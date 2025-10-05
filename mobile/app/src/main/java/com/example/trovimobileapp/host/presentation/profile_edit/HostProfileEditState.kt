package com.example.trovimobileapp.host.presentation.profile_edit

import com.example.trovimobileapp.core.domain.UiText
import com.example.trovimobileapp.core.domain.model.User

data class HostProfileEditState(
    val message: UiText? = null,
    val isLoadingUser: Boolean = false,
    val user: User? = null,
)