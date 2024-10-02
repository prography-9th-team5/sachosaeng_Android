package com.sachosaeng.app.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sachosaeng.app.core.model.Category

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryListFlowRow(
    list: List<Category>,
    onCategoryList: List<Category>? = null,
    onClickCategory: (Category) -> Unit,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(top = 32.dp),
) {
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        list.forEach {
            CircleCategoryButton(
                isOn = onCategoryList?.contains(it) ?: false,
                category = it,
                onClickCategory = { onClickCategory(it) },
                modifier = Modifier.padding(horizontal = 12.dp).weight(1f)
            )
        }
        when(list.size % 3) {
            1 -> {
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.weight(1f))
            }
            2 -> Spacer(modifier = Modifier.weight(1f))
        }
    }
}
