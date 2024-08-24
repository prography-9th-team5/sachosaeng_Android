package com.example.sachosaeng.core.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.model.Category

@Composable
fun CategoryTitleText(category: Category) {
    Text(
        text = "#${category.name}",
        color = Color(category.color.toLong(16).toInt()),
        fontSize = 18.sp,
        fontWeight = FontWeight.W700
    )
}