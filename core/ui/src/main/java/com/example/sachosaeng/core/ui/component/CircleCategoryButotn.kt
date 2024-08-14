package com.example.sachosaeng.core.ui.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.ui.R

@Composable
fun CircleCategoryButton(category: Category, onClickCategory: (Category) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            contentDescription = "",
            model = category.imageUrl ?: R.drawable.if_default_category_circle,
            modifier = Modifier
                .clip(CircleShape)
                .size(84.dp)
                .clickable { onClickCategory(category) }
        )
        Text(
            text = category.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}