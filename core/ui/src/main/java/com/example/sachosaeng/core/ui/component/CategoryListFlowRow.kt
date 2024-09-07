package com.example.sachosaeng.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.model.Category

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryListFlowRow(
    list: List<Category>,
    selectedCategoryList: List<Category>? = null,
    onClickCategory: (Category) -> Unit,
    modifier: Modifier = Modifier,
) =
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        list.forEach {
            CircleCategoryButton(
                isSelected = selectedCategoryList?.contains(it) ?: false,
                category = it,
                onClickCategory = { onClickCategory(it) },
                modifier = Modifier.padding(horizontal = 23.dp)
            )
        }
    }
