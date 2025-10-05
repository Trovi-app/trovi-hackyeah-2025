package com.example.trovimobileapp.guest.presentation.host_profile

import com.example.trovimobileapp.core.domain.UiText
import com.example.trovimobileapp.core.domain.model.User

data class HostProfileState(
    val message: UiText? = null,
    val isLoadingUser: Boolean = false,
    val user: User? = null,
    val createdChatId: Int? = null
)