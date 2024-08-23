package com.example.sachosaeng.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.util.extension.StringExtension.toColorResource

@Composable
fun CircleCategoryButton(
    isSelected: Boolean = false,
    category: Category,
    onClickCategory: (Category) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            contentDescription = "",
            model = category.imageUrl ?: R.drawable.if_default_category_circle,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onClickCategory(category) }
                .size(72.dp)
                .background(color = Color(category.color.toColorResource()))
                .border(
                    width = 1.dp,
                    color = if (isSelected) Color.Black else Color.Transparent,
                    shape = CircleShape
                )
                .padding(20.dp)
        )
        Text(
            text = category.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}