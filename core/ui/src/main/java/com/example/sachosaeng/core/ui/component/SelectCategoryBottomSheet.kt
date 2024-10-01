package com.example.sachosaeng.core.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoryBottomSheet(
    modifier: Modifier = Modifier,
    allCategoryList: List<Category>,
    myCategoryList: List<Category>,
    onDismissRequest: () -> Unit = { },
    onSelectCategory: (Category) -> Unit,
    onSelectFavoriteCategory: (Category) -> Unit,
    onModifyMyCategoryButtonClicked: () -> Unit,
    onModifyComplete: () -> Unit,
    modifyListVisible: Boolean = false
) {
    val sheetState = rememberModalBottomSheetState()
    val myCategoryTabTitle = stringResource(id = R.string.my_category)
    val allCategoryTabTitle = stringResource(id = R.string.all_category)
    val tabList = listOf(myCategoryTabTitle, allCategoryTabTitle)

    ModalBottomSheet(
        modifier = modifier,
        containerColor = Gs_White,
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() }) {
        TabRowComponent(
            tabs = tabList,
            tabTrailingButtonComposition = tabList.indexOf(myCategoryTabTitle),
            tabTrailingButton = {
                if (!modifyListVisible) ModifyCategoryButton(
                    onClick = onModifyMyCategoryButtonClicked
                )
            },
            contentScreens = listOf(
                {
                    when (modifyListVisible) {
                        true -> CategoryFlowRowWithButton(
                            allCategoryList.drop(1),
                            onCategoryList = myCategoryList,
                            onClickCategory = onSelectFavoriteCategory,
                            onClickButton = onModifyComplete
                        )

                        false -> CategoryListFlowRow(
                            myCategoryList,
                            onCategoryList = myCategoryList,
                            onClickCategory = onSelectCategory
                        )
                    }
                },
                {
                    CategoryListFlowRow(
                        allCategoryList,
                        onCategoryList = allCategoryList,
                        onClickCategory = onSelectCategory
                    )
                }
            )
        )
    }
}

@Composable
fun ModifyCategoryButton(onClick: () -> Unit = {}) {
    Text(
        modifier = Modifier
            .padding(top = 10.dp)
            .noRippleClickable(onClick = onClick),
        text = stringResource(id = R.string.modify_label),
        color = Gs_G5,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500
    )
}

@Composable
private fun CategoryFlowRowWithButton(
    categoryList: List<Category>,
    onCategoryList: List<Category>? = null,
    onClickCategory: (Category) -> Unit,
    onClickButton: () -> Unit = {}
) {
    Column {
        CategoryListFlowRow(
            categoryList,
            onCategoryList = onCategoryList,
            onClickCategory = onClickCategory,
        )
        Spacer(modifier = Modifier.weight(1f))
        SachoSaengButton(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(start = 20.dp, end = 20.dp),
            text = stringResource(id = R.string.complete_label),
            onClick = onClickButton
        )
    }
}