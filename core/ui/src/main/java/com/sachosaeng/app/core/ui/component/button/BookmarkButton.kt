package com.sachosaeng.app.core.ui.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G5

@Composable
fun BookmarkButton(
    modifier: Modifier = Modifier,
    isBookmarked: Boolean = false,
    onBookmarkButtonClicked: () -> Unit = { }
) {
    IconButton(
        modifier = modifier
            .size(68.dp),
        onClick = { onBookmarkButtonClicked() }) {
        Icon(
            painterResource(id = R.drawable.ic_bookmark),
            tint = if (isBookmarked) Gs_Black else Gs_G5,
            contentDescription = ""
        )
    }
}