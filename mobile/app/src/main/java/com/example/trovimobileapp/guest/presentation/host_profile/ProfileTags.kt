package com.example.trovimobileapp.guest.presentation.host_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trovimobileapp.R
import com.example.trovimobileapp.core.domain.model.Tag
import com.example.trovimobileapp.ui.theme.TroviMobileAppTheme

@Composable
fun ProfileTags(
    tags: List<Tag>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        tags.forEach { tag ->
            SuggestionChip(
                onClick = {},
                label = {
                    Text(text = tag.name)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileTagsPreview() {
    TroviMobileAppTheme {
        ProfileTags(
            tags = listOf()
        )
    }
}

