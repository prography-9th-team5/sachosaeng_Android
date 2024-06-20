package com.example.sachosaeng.feature.util.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.feature.R

@Composable
fun CircleCategoryIcon(category: Category, onClickCategory: (Category) -> Unit) {
    AsyncImage(
        contentDescription = "", model = category.imageUrl ?: R.drawable.if_default_category_circle,
        modifier = Modifier
            .clip(CircleShape)
            .size(84.dp)
            .clickable { onClickCategory(category) }
    )
}