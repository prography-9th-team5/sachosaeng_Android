package com.sachosaeng.app.core.ui.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sachosaeng.app.core.ui.component.button.SachoSaengButton
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_White

@Composable
fun SachosaengOneButtonDialog(
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonOnClick: () -> Unit,
    content: @Composable () -> Unit
) {
    SachosaengDialog(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            content()
            SachoSaengButton(
                modifier = Modifier.sizeIn(minWidth = 216.dp, minHeight = 47.dp),
                buttonColors = ButtonDefaults.buttonColors()
                    .copy(containerColor = Gs_Black, contentColor = Gs_White),
                text = buttonText,
                onClick = { buttonOnClick() })
        }
    }
}