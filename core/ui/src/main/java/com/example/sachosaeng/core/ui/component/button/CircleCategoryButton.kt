package com.sachosaeng.app.core.ui.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G4
import com.sachosaeng.app.core.util.extension.StringExtension.toColorResource

@Composable
fun CircleCategoryButton(
    isOn: Boolean = false,
    category: Category,
    onClickCategory: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            contentDescription = "",
            colorFilter = if (!isOn) ColorFilter.tint(Gs_G4) else null,
            model = category.imageUrl ?: R.drawable.if_default_category_circle,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onClickCategory(category) }
                .size(72.dp)
                .background(color = if (!isOn) Gs_G2 else Color(category.color.toColorResource()))
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