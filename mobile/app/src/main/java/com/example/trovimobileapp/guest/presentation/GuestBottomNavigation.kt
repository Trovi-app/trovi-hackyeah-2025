package com.example.trovimobileapp.guest.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trovimobileapp.core.presentation.TroviAppScreen
import com.example.trovimobileapp.ui.theme.TroviMobileAppTheme

@Composable
fun GuestBottomNavigation(
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

@Preview(showBackground = true)
@Composable
private fun GuestBottomNavigationPreview() {
    TroviMobileAppTheme {
        GuestBottomNavigation(
            navigationScreens = listOf(TroviAppScreen.GuestHome),
            currentScreen = TroviAppScreen.GuestHome,
            onNavigate = {}
        )
    }
}

