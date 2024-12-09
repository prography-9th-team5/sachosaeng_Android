package com.example.sachosaeng.core.ui.component.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit
) {
        IconButton(onClick = { onCloseClick() }, modifier = modifier) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
}