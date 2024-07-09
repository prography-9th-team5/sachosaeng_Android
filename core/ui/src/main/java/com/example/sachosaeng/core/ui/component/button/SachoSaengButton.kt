package com.example.sachosaeng.core.ui.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G4
import com.example.sachosaeng.core.ui.theme.Gs_White


@Composable
fun SachoSaengButton(
    modifier: Modifier = Modifier,
    containerColor: Color = Gs_Black,
    enabled: Boolean = true,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = containerColor,
            disabledContainerColor = Gs_G4
        ),
        shape = RoundedCornerShape(4.dp),
        onClick = { onClick() }) {
        Text(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = text,
            fontSize = 16.sp,
            color = Gs_White,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W600
        )
    }
}