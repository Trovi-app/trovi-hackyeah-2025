package com.example.trovimobileapp.core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.trovimobileapp.R
import com.example.trovimobileapp.core.domain.model.Comment

@Composable
fun UserReviewsInfo(
    commentsAsHost: List<Comment>,
    modifier: Modifier = Modifier
) {
    if (commentsAsHost.isNotEmpty()) {
        Row(modifier = modifier) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null
            )
            Text(
                text = "${"%.2f".format(commentsAsHost.map { it.rating }.average())} (${commentsAsHost.size} reviews)",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
            )
        }
    } else {
        Text(
            text = "0 reviews",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
        )
    }
}