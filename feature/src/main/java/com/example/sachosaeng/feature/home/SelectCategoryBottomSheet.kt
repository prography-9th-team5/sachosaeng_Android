package com.example.sachosaeng.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.feature.R
import com.example.sachosaeng.feature.util.component.CircleCategoryButton
import com.example.sachosaeng.feature.util.component.TabRowComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoryBottomSheet(
    allCategoryList: List<Category>,
    myCategoryList: List<Category>,
    onDismissRequest: () -> Unit = { },
    onSelectCategory: (Category) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        containerColor = Gs_White,
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() }) {
        TabRowComponent(
            tabs = listOf(
                stringResource(id = R.string.my_category),
                stringResource(id = R.string.all_category),
            ),
            contentScreens = listOf(
                {
                    Column {
                        CategoryListFlowRow(myCategoryList, onSelectCategory)
                        SachoSaengButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.confirm_label),
                            onClick = {}
                        )
                    }
                },
                { CategoryListFlowRow(allCategoryList, onSelectCategory) }
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryListFlowRow(list: List<Category>, onSelectCategory: (Category) -> Unit) =
    FlowRow(
        modifier = Modifier.padding(top = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        list.forEach {
            CircleCategoryButton(
                category = it,
                onClickCategory = { onSelectCategory(it) })
        }
    }
