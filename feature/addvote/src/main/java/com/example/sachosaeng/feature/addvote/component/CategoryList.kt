package com.example.sachosaeng.feature.addvote.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.ui.component.CategoryCardWithoutBorder
import com.sachosaeng.app.core.model.Category

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CategoryList(
    modifier: Modifier = Modifier,
    selectedCategory: List<Category> = emptyList(),
    categories: List<Category> = emptyList(),
    onCategorySelected: (Category) -> Unit
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach {
            CategoryCardWithoutBorder(
                isSelected = selectedCategory.contains(it),
                category = it,
                onCategoryClicked = onCategorySelected
            )
        }
    }
}