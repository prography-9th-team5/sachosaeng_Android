package com.example.sachosaeng.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.util.extension.StringExtension.toColorResource

@Composable
fun CategoryTitleText(modifier: Modifier = Modifier, category: Category) {
    Row(
        modifier = modifier.padding(bottom = 12.dp, top = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            modifier = modifier.size(18.dp),
            model = if (category.imageUrl.isNullOrEmpty()) R.drawable.ic_hot_vote else category.imageUrl,
            contentDescription = "",
        )
        Text(
            text = category.name,
            color = Color(category.textColor.toColorResource()),
            fontSize = 16.sp,
            fontWeight = FontWeight.W700
        )
    }
}