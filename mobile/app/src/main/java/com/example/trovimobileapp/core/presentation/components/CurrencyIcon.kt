package com.example.trovimobileapp.core.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.trovimobileapp.R

@Composable
fun CurrencyIcon(
    size: Int = 32,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(R.drawable.ic_currency),
        contentDescription = null,
        tint = Color(0xFFFFB367),
        modifier = modifier.size(size.dp)
    )
}

