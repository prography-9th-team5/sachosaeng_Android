package com.example.sachosaeng.core.ui.component.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    onCloseClick: () -> Unit
) {
    IconButton(
        onClick = { onCloseClick() },
        modifier = modifier,
        colors = colors
    ) {
        Icon(imageVector = Icons.Default.Close, contentDescription = null)
    }
}