package com.sachosaeng.app.feature.bookmark.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.component.CategoryCard
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G5

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
