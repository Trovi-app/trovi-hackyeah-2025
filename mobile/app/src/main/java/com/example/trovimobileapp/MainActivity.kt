package com.example.trovimobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.trovimobileapp.core.presentation.TroviAppNavigation
import com.example.trovimobileapp.ui.theme.TroviMobileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TroviMobileAppTheme {
                TroviAppNavigation()
            }
        }
    }
}