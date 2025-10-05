package com.example.trovimobileapp.core.presentation

import com.example.trovimobileapp.R
import kotlinx.serialization.Serializable

@Serializable
sealed class TroviAppScreen(
    val route: String,
    val title: String? = null,
    val icon: Int? = null
) {

    @Serializable
    data object Login: TroviAppScreen("Login")

    @Serializable
    data object ContinueAs: TroviAppScreen("ContinueAs")

    @Serializable
    data object Guest: TroviAppScreen("Guest")

    @Serializable
    data object GuestHome: TroviAppScreen("GuestHome", "Home", R.drawable.ic_home)

    @Serializable
    data class HostProfile(val hostId: Int): TroviAppScreen("GuestProfile")

    @Serializable
    data object Chats: TroviAppScreen("Chats", "Chats", R.drawable.ic_chat)

    @Serializable
    data class Chat(val id: Int): TroviAppScreen("Chat")

    @Serializable
    data object Host: TroviAppScreen("Host")

    @Serializable
    data object HostChats: TroviAppScreen("HostChats", "Chats", R.drawable.ic_chat)

    @Serializable
    data class HostChat(val id: Int): TroviAppScreen("HostChat")

    @Serializable
    data object HostProfileEdit: TroviAppScreen("HostProfileEdit", "Profile", R.drawable.ic_user)

}