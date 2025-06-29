package com.example.sachosaeng.core.ui.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sachosaeng.app.core.ui.component.button.SachoSaengButton
import com.sachosaeng.app.core.ui.component.dialog.SachosaengDialog
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G4

@Composable
fun SachosaengTwoButtonDialog(
    modifier: Modifier = Modifier,
    leftButtonText: String,
    leftButtonOnClick: () -> Unit,
    rightButtonText: String,
    rightButtonOnClick: () -> Unit,
    fontSize: Int? = null,
    content: @Composable () -> Unit
) {
    SachosaengDialog(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            content()
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                SachoSaengButton(
                    modifier = modifier.weight(.5f),
                    buttonColors = ButtonDefaults.buttonColors()
                        .copy(containerColor = Gs_G4, contentColor = Gs_Black),
                    text = leftButtonText,
                    fontSize = fontSize,
                    onClick = { leftButtonOnClick() })
                Spacer(modifier = modifier.padding(end = 10.dp))
                SachoSaengButton(
                    modifier = modifier.weight(.5f),
                    fontSize = fontSize,
                    text = rightButtonText,
                    onClick = { rightButtonOnClick() })
            }
        }
    }
}