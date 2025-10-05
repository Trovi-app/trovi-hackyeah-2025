package com.example.trovimobileapp.host.presentation.profile_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trovimobileapp.core.data.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HostProfileEditViewModel(
    private val hostId: Int,
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _hostProfileEditState = MutableStateFlow(HostProfileEditState())
    val hostProfileEditState = _hostProfileEditState.asStateFlow()

    init {
        getUserById()
    }

    private fun getUserById() {
        _hostProfileEditState.update {
            it.copy(isLoadingUser = true)
        }
        viewModelScope.launch {
            val user = usersRepository.getUserById(hostId)
            _hostProfileEditState.update {
                it.copy(
                    user = user,
                    isLoadingUser = false
                )
            }
        }
    }

    fun onHostProfileEditScreenEvent(event: HostProfileEditScreenEvent) {
        when (event) {
            else -> TODO("Handle actions")
        }
    }

}