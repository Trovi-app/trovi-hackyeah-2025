package com.example.trovimobileapp.host.presentation.chat

sealed interface HostChatScreenEvent {

    data object GoBack: HostChatScreenEvent

    data class MessageChanged(val message: String): HostChatScreenEvent

    data object SendMessage: HostChatScreenEvent

    data class CostChanged(val cost: String): HostChatScreenEvent

    data object SendCostMessage: HostChatScreenEvent

    data object SendMeetingMessage: HostChatScreenEvent

}