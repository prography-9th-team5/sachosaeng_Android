package com.example.sachosaeng.core.ui.component.topappbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.component.button.CloseButton
import com.sachosaeng.app.core.ui.theme.Gs_G6

@Composable
fun SachosaengTopAppBarWithCloseButton(
    modifier: Modifier = Modifier,
    title: String? = null,
    onCloseClick: () -> Unit,
) {
    Box(modifier = modifier.fillMaxWidth()) {
            title?.let { Text(it, modifier = modifier.align(Alignment.Center), fontSize = 18.sp, color = Gs_G6, fontWeight = FontWeight.W700) }
            CloseButton(modifier = modifier.align(Alignment.TopEnd)) { onCloseClick() }
        }
    }