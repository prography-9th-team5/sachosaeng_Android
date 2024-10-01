package com.sachosaeng.app.feature.bookmark.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.util.extension.StringExtension.toColorResource
import com.sachosaeng.app.feature.bookmark.BookmarkScreenUiState

@Composable
fun CategoryRow(
    selectedCategory: Category? = null,
    categories: List<Category>,
    modifier: Modifier = Modifier,
    onCategoryClicked: (Category) -> Unit = {},
    onModifyButtonClicked: () -> Unit = {},
    isModifyMode: Boolean = false
) {
    Box {
        LazyRow(
            modifier = modifier.padding(top = 16.dp, bottom = 16.dp, end = 60.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(categories.size) {
                CategoryCard(
                    isSelected = selectedCategory == categories[it],
                    category = categories[it],
                    onCategoryClicked = onCategoryClicked
                )
            }
        }
        Row(
            modifier = Modifier
                .background(Gs_G2)
                .padding(16.dp)
                .align(Alignment.CenterEnd)
                .noRippleClickable { onModifyButtonClicked() }
        ) {
            Text(
                color = if (isModifyMode) Gs_G5 else Gs_Black,
                text = stringResource(id = if (isModifyMode) R.string.cancel_label else R.string.modify_label),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500
            )
        }
    }
}

@Composable
private fun CategoryCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    category: Category,
    onCategoryClicked: (Category) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Color(category.color.toColorResource()),
            contentColor = Color(category.textColor.toColorResource())
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .border(
                1.dp,
                if (isSelected) Color(category.textColor.toColorResource()) else Color.Transparent,
                RoundedCornerShape(4.dp)
            )
            .clickable {
                onCategoryClicked(category)
            },
    ) {
        Row(
            modifier = modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (category.imageUrl?.isNotEmpty() == true) {
                AsyncImage(
                    alignment = Alignment.CenterEnd,
                    contentDescription = "", model = category.imageUrl,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 8.dp)
                )
            }
            Text(
                text = category.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}
