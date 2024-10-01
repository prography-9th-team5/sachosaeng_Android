package com.sachosaeng.app.core.ui.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sachosaeng.app.core.ui.theme.Gs_G3

@Composable
fun SachosaengDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors().copy(containerColor = Gs_G3)
        ) {
            Column(modifier = modifier.padding(16.dp)) {
                content()
            }
        }
    }
}