package com.example.sachosaeng.core.ui.component.topappbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.ui.R

@Composable
fun SachosaengTopAppBar(
    componentRow: @Composable () -> Unit,
    profileImageUrl: String? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        componentRow()
        ProfileImage(profileImageUrl)
    }
}

@Composable
fun ProfileImage(profileImageUrl: String?) {
    AsyncImage(
        modifier = Modifier.size(40.dp),
        model = profileImageUrl ?: R.drawable.ic_default_profile,
        contentDescription = "",
    )
}