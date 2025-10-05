package com.example.trovimobileapp.core.presentation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.trovimobileapp.chat.presentation.chat.ChatScreen
import com.example.trovimobileapp.chat.presentation.chat.ChatScreenEvent
import com.example.trovimobileapp.chat.presentation.chat.ChatViewModel
import com.example.trovimobileapp.chat.presentation.chat_list.ChatListScreen
import com.example.trovimobileapp.chat.presentation.chat_list.ChatListScreenEvent
import com.example.trovimobileapp.chat.presentation.chat_list.ChatListViewModel
import com.example.trovimobileapp.guest.presentation.GuestBottomNavigation
import com.example.trovimobileapp.guest.presentation.home.GuestHomeScreen
import com.example.trovimobileapp.guest.presentation.home.GuestHomeScreenEvent
import com.example.trovimobileapp.guest.presentation.home.GuestHomeViewModel
import com.example.trovimobileapp.guest.presentation.host_profile.HostProfileScreen
import com.example.trovimobileapp.guest.presentation.host_profile.HostProfileScreenEvent
import com.example.trovimobileapp.guest.presentation.host_profile.HostProfileViewModel
import com.example.trovimobileapp.host.presentation.HostBottomNavigation
import com.example.trovimobileapp.host.presentation.chat.HostChatScreen
import com.example.trovimobileapp.host.presentation.chat.HostChatScreenEvent
import com.example.trovimobileapp.host.presentation.chat.HostChatViewModel
import com.example.trovimobileapp.host.presentation.chat_list.HostChatListScreen
import com.example.trovimobileapp.host.presentation.chat_list.HostChatListScreenEvent
import com.example.trovimobileapp.host.presentation.chat_list.HostChatListViewModel
import com.example.trovimobileapp.host.presentation.profile_edit.HostProfileEditScreen
import com.example.trovimobileapp.host.presentation.profile_edit.HostProfileEditViewModel
import com.example.trovimobileapp.login.presentation.LoginScreen
import com.example.trovimobileapp.login.presentation.LoginViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TroviAppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val guestBottomNavigationScreens = listOf(TroviAppScreen.GuestHome, TroviAppScreen.Chats)
    val hostBottomNavigationScreens = listOf(TroviAppScreen.HostChats, TroviAppScreen.HostProfileEdit)
    Scaffold(
        bottomBar = {
            if (currentRoute != null && currentRoute.getScreenFromRoute() in guestBottomNavigationScreens) {
                GuestBottomNavigation(
                    navigationScreens = guestBottomNavigationScreens,
                    currentScreen = currentRoute.getScreenFromRoute(),
                    onNavigate = { screen ->
                        navController.navigate(screen)
                    }
                )
            } else if (currentRoute != null && currentRoute.getScreenFromRoute() in hostBottomNavigationScreens) {
                HostBottomNavigation(
                    navigationScreens = hostBottomNavigationScreens,
                    currentScreen = currentRoute.getScreenFromRoute(),
                    onNavigate = { screen ->
                        navController.navigate(screen)
                    }
                )
            }
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        val loginViewModel = koinViewModel<LoginViewModel>()
        val loginState = loginViewModel.loginState
        NavHost(
            navController = navController,
            startDestination = if (loginState.loggedInUser != null) TroviAppScreen.ContinueAs else TroviAppScreen.Login,
        ) {
            composable<TroviAppScreen.Login> {
                LoginScreen(
                    loginState = loginState,
                    onLoginScreenEvent = loginViewModel::onLoginScreenEvent,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            composable<TroviAppScreen.ContinueAs> {
                ContinueAsScreen(
                    onContinueAsGuest = {
                        navController.navigate(TroviAppScreen.Guest)
                    },
                    onContinueAsHost = {
                        navController.navigate(TroviAppScreen.Host)
                    }
                )
            }
            navigation<TroviAppScreen.Guest>(startDestination = TroviAppScreen.GuestHome) {
                composable<TroviAppScreen.GuestHome> {
                    val guestHomeViewModel = koinViewModel<GuestHomeViewModel>()
                    val guestHomeState by guestHomeViewModel.guestHomeState.collectAsStateWithLifecycle()
                    GuestHomeScreen(
                        loggedUser = loginState.loggedInUser!!,
                        guestHomeState = guestHomeState,
                        onGuestHomeScreenEvent = { event ->
                            when (event) {
                                is GuestHomeScreenEvent.GoToHostProfile -> {
                                    navController.navigate(TroviAppScreen.HostProfile(event.hostId))
                                }
                                else -> Unit
                            }
                            guestHomeViewModel.onGuestHomeScreenEvent(event)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                composable<TroviAppScreen.HostProfile> { backStackEntry ->
                    val hostProfile: TroviAppScreen.HostProfile = backStackEntry.toRoute()
                    val hostProfileViewModel = koinViewModel<HostProfileViewModel>(
                        parameters = {
                            parametersOf(
                                loginState.loggedInUser!!.id,
                                hostProfile.hostId
                            )
                        }
                    )
                    val hostProfileState by hostProfileViewModel.hostProfileState.collectAsStateWithLifecycle()
                    HostProfileScreen(
                        hostProfileState = hostProfileState,
                        onHostProfileScreenEvent = { event ->
                            when (event) {
                                is HostProfileScreenEvent.NavigateToChat -> {
                                    navController.navigate(TroviAppScreen.Chat(event.chatId))
                                }
                                else -> Unit
                            }
                            hostProfileViewModel.onHostProfileScreenEvent(event)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                composable<TroviAppScreen.Chats> {
                    val chatListViewModel = koinViewModel<ChatListViewModel>(
                        parameters = { parametersOf(loginState.loggedInUser!!.id) }
                    )
                    val chatListState by chatListViewModel.chatListState.collectAsStateWithLifecycle()
                    ChatListScreen(
                        loggedInUser = loginState.loggedInUser!!,
                        chatListState = chatListState,
                        onChatListScreenEvent = { event ->
                            when (event) {
                                is ChatListScreenEvent.GoToChat -> {
                                    navController.navigate(TroviAppScreen.Chat(event.chatId))
                                }
                            }
                            chatListViewModel.onChatListScreenEvent(event)
                        },
                    )
                }
                composable<TroviAppScreen.Chat> { backStackEntry ->
                    val chat: TroviAppScreen.Chat = backStackEntry.toRoute()
                    val chatViewModel = koinViewModel<ChatViewModel>(
                        parameters = { parametersOf(chat.id, loginState.loggedInUser!!.id) }
                    )
                    val chatState by chatViewModel.chatState.collectAsStateWithLifecycle()
                    ChatScreen(
                        chatState = chatState,
                        onChatScreenEvent = { event ->
                            when (event) {
                                is ChatScreenEvent.GoBack -> {
                                    navController.popBackStack()
                                }
                                is ChatScreenEvent.NavigateToProfile -> {
                                    navController.navigate(TroviAppScreen.HostProfile(event.hostId))
                                }
                                else -> Unit
                            }
                            chatViewModel.onChatScreenEvent(event)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
            navigation<TroviAppScreen.Host>(startDestination = TroviAppScreen.HostChats) {
                composable<TroviAppScreen.HostChats> {
                    val hostChatListViewModel = koinViewModel<HostChatListViewModel>(
                        parameters = { parametersOf(loginState.loggedInUser!!.id) }
                    )
                    val hostChatListState by hostChatListViewModel.hostChatListState.collectAsStateWithLifecycle()
                    HostChatListScreen(
                        loggedInUser = loginState.loggedInUser!!,
                        hostChatListState = hostChatListState,
                        onHostChatListScreenEvent = { event ->
                            when (event) {
                                is HostChatListScreenEvent.GoToChat -> {
                                    navController.navigate(TroviAppScreen.HostChat(event.chatId))
                                }
                            }
                            hostChatListViewModel.onChatListScreenEvent(event)
                        },
                    )
                }
                composable<TroviAppScreen.HostChat> {
                    val hostChat: TroviAppScreen.HostChat = it.toRoute()
                    val hostChatViewModel = koinViewModel<HostChatViewModel>(
                        parameters = { parametersOf(hostChat.id, loginState.loggedInUser!!.id) }
                    )
                    val hostChatState by hostChatViewModel.chatState.collectAsStateWithLifecycle()
                    HostChatScreen(
                        hostChatState = hostChatState,
                        onHostChatScreenEvent = { event ->
                            when (event) {
                                is HostChatScreenEvent.GoBack -> {
                                    navController.popBackStack()
                                }
                                else -> Unit
                            }
                            hostChatViewModel.onHostChatScreenEvent(event)
                        }
                    )
                }
                composable<TroviAppScreen.HostProfileEdit> {
                    val hostProfileEditViewModel = koinViewModel<HostProfileEditViewModel>(
                        parameters = {
                            parametersOf(loginState.loggedInUser!!.id)
                        }
                    )
                    val hostProfileEditState by hostProfileEditViewModel.hostProfileEditState.collectAsStateWithLifecycle()
                    HostProfileEditScreen(
                        hostProfileEditState = hostProfileEditState,
                        onHostProfileEditScreenEvent = hostProfileEditViewModel::onHostProfileEditScreenEvent,
                    )
                }
            }
        }
    }
}

fun String.getScreenFromRoute(): TroviAppScreen {
    val screenRoute = this.substringAfterLast('.').substringBefore('/')
    return when (screenRoute) {
        "Login" -> TroviAppScreen.Login
        "ContinueAs" -> TroviAppScreen.ContinueAs
        "Guest" -> TroviAppScreen.Guest
        "GuestHome" -> TroviAppScreen.GuestHome
        "HostProfile" -> TroviAppScreen.HostProfile(1)
        "Chats" -> TroviAppScreen.Chats
        "Chat" -> TroviAppScreen.Chat(1)
        "Host" -> TroviAppScreen.Host
        "HostChats" -> TroviAppScreen.HostChats
        "HostChat" -> TroviAppScreen.HostChat(1)
        "HostProfileEdit" -> TroviAppScreen.HostProfileEdit
        else -> TroviAppScreen.GuestHome
    }
}