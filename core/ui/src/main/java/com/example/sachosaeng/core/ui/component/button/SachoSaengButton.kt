package com.example.sachosaeng.core.ui.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G4


@Composable
fun SachoSaengButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors().copy(containerColor = Gs_Black),
    enabled: Boolean = true,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = buttonColors.containerColor,
            disabledContainerColor = Gs_G4
        ),
        shape = RoundedCornerShape(4.dp),
        onClick = { onClick() }) {
        Text(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = text,
            fontSize = 16.sp,
            color = buttonColors.contentColor,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W600
        )
    }
}