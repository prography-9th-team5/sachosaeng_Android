package com.example.sachosaeng.feature.mypage.modifyCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.component.CategoryListFlowRow
import com.example.sachosaeng.core.ui.component.DetailScreenTopbar
import com.example.sachosaeng.core.ui.component.ModifyCategoryButton
import com.example.sachosaeng.core.ui.component.TabRowComponent
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_White
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ModifyCategoryScreen(
    showSnackBar: (String) -> Unit = {},
    navigateToBackStack: () -> Unit = {},
    viewModel: ModifyCategoryViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is ModifyCategorySideEffect.ShowSnackBar -> showSnackBar(it.message)
        }
    }

    ModifyCategoryScreen(
        onSelectedCategory = viewModel::selectCategory,
        onModifyComplete = viewModel::modifyComplete,
        onModifyMyCategoryButtonClicked = viewModel::modifyMyCategory,
        navigateToBackStack = navigateToBackStack,
        myPageUiState = state,
    )
}

@Composable
internal fun ModifyCategoryScreen(
    onModifyComplete: () -> Unit = {},
    onModifyMyCategoryButtonClicked: () -> Unit = {},
    onSelectedCategory: (Category) -> Unit = {},
    navigateToBackStack: () -> Unit = {},
    myPageUiState: ModifyCategoryUiState,
) {
    val scrollState = rememberScrollState()
    val tab = listOf(stringResource(id = R.string.my_category))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gs_White)
            .verticalScroll(scrollState)
    ) {
        DetailScreenTopbar(
            modifier = Modifier
                .background(color = Gs_G2)
                .padding(horizontal = 20.dp),
            pageLabel = stringResource(id = R.string.modify_category_title),
            navigateToBackStack = navigateToBackStack
        )
        TabRowComponent(
            tabTrailingButton = {
                if (myPageUiState.modifyButtonVisibility) ModifyCategoryButton(
                    onClick = onModifyMyCategoryButtonClicked
                )
            },
            modifier = Modifier.background(color = Gs_G2),
            tabs = tab,
            tabTrailingButtonComposition = 0,
            contentScreens = listOf {
                CategoryListFlowRow(
                    list = myPageUiState.visibleList,
                    onCategoryList = myPageUiState.selectedList,
                    onClickCategory = onSelectedCategory
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        if (!myPageUiState.modifyButtonVisibility) SachoSaengButton(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(20.dp),
            text = stringResource(id = R.string.complete_label),
            onClick = onModifyComplete
        )
    }
}


@Preview
@Composable
fun ModifyCategoryScreenPreview() {
    ModifyCategoryScreen(
        navigateToBackStack = {},
        onSelectedCategory = {},
        myPageUiState = ModifyCategoryUiState(
            visibleList = listOf(
                Category(1, "category1"),
                Category(2, "category2"),
                Category(3, "category3"),
                Category(4, "category4"),
                Category(5, "category5"),
                Category(6, "category6"),
                Category(7, "category7")
            ),
            selectedList = listOf(
                Category(1, "category1"),
                Category(2, "category2"),
                Category(3, "category3")
            ),
            modifyButtonVisibility = false
        )
    )
}