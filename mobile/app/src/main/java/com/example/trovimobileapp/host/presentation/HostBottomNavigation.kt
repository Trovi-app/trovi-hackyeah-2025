package com.example.trovimobileapp.host.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.trovimobileapp.core.presentation.TroviAppScreen

@Composable
fun HostBottomNavigation(
    navigationScreens: List<TroviAppScreen>,
    currentScreen: TroviAppScreen,
    onNavigate: (TroviAppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        navigationScreens.forEach { navigationScreen ->
            NavigationBarItem(
                selected = currentScreen == navigationScreen,
                onClick = { onNavigate(navigationScreen) },
                icon = {
                    Icon(
                        painter = painterResource(navigationScreen.icon!!),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = navigationScreen.title!!,
                    )
                }
            )
        }
    }
}