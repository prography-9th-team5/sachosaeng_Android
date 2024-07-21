package com.example.sachosaeng.feature.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.theme.Gs_G6

@Composable
fun SelectScreenDescription(title: String, subText: String) {
    Column {
        Text(
            textAlign = TextAlign.Start,
            text = title,
            fontSize = 26.sp,
            fontWeight = FontWeight.W700
        )
        Text(
            text = subText,
            fontSize = 16.sp,
            color = Gs_G6,
            fontWeight = FontWeight.W500
        )
    }
}