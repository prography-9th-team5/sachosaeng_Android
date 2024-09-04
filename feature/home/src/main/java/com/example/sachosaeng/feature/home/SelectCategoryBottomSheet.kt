package com.example.sachosaeng.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.component.CategoryListFlowRow
import com.example.sachosaeng.core.ui.component.TabRowComponent
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoryBottomSheet(
    modifier: Modifier = Modifier,
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
                        CategoryListFlowRow(
                            allCategoryList,
                            myCategoryList,
                            onSelectCategory,
                            modifier = modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        SachoSaengButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .navigationBarsPadding()
                                .padding(start = 20.dp, end = 20.dp),
                            text = stringResource(id = R.string.complete_label),
                            onClick = {}
                        )
                    }
                },
                { CategoryListFlowRow(allCategoryList, myCategoryList, onSelectCategory) }
            )
        )
    }
}
