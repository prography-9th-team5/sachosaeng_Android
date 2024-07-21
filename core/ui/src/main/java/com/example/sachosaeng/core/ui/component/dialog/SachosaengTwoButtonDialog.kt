package com.example.sachosaeng.core.ui.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G4

@Composable
fun SachosaengTwoButtonDialog(
    modifier: Modifier = Modifier,
    leftButtonText: String,
    leftButtonOnClick: () -> Unit,
    rightButtonText: String,
    rightButtonOnClick: () -> Unit,
    content: @Composable () -> Unit
) {
    SachosaengDialog(modifier = modifier) {
        Column {
            content()
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                SachoSaengButton(
                    buttonColors = ButtonDefaults.buttonColors()
                        .copy(containerColor = Gs_G4, contentColor = Gs_Black),
                    text = leftButtonText,
                    onClick = { leftButtonOnClick() })
                SachoSaengButton(
                    text = rightButtonText,
                    onClick = { rightButtonOnClick() })
            }
        }
    }
}