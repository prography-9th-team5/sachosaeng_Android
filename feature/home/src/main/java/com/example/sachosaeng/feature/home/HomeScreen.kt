package com.example.sachosaeng.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.component.topappbar.TopBarWithProfileImage
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.util.constant.IntConstant.ALL_CATEGORY_ID
import com.example.sachosaeng.feature.home.component.ListByCategory
import com.example.sachosaeng.feature.home.component.MainList
import com.example.sachosaeng.feature.home.component.SelectCategoryBottomSheet
import com.example.sachosaeng.feature.home.component.TodaysVoteDialog
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    moveToMyPage: () -> Unit = {},
    navigateToVoteCard: (Int, Boolean) -> Unit = { _, _ -> },
    viewModel: HomeViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    val state = viewModel.collectAsState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    viewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.NavigateToVoteDetail -> navigateToVoteCard(it.voteId, it.isDailyVote)
            else -> {}
        }
    }

    if (state.value.isDailyVoteDialogOpen) TodaysVoteDialog(
        onClick = {
            viewModel.onDailyVoteDialogConfirmClicked()
        }
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Gs_G2)
            .padding(20.dp)
    ) {
        val scope = rememberCoroutineScope()
        Column {
            TopBarWithProfileImage(
                modifier = modifier,
                topBarContent = {
                    CategorySelectButton(
                        selectedCategory = state.value.selectedCategory,
                        onSelectCategory = { isBottomSheetOpen = true }
                    )
                },
                userType = state.value.userType,
                onProfileImageClicked = {
                    moveToMyPage()
                }
            )
            if (state.value.selectedCategory.id == ALL_CATEGORY_ID) MainList(
                state = state.value,
                listState = listState,
                navigateToVoteCard = navigateToVoteCard
            )
            else ListByCategory(
                state = state.value,
                navigateToVoteCard = navigateToVoteCard
            )
        }
        Image(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .noRippleClickable {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
            painter = painterResource(id = drawable.ic_floating_button),
            contentDescription = null
        )
    }
    if (isBottomSheetOpen) {
        SelectCategoryBottomSheet(
            allCategoryList = state.value.allCategory,
            myCategoryList = state.value.myCategory,
            onModifyMyCategoryButtonClicked = viewModel::onModifyMyCategory,
            onModifyComplete = {
                viewModel.onModifyComplete()
                isBottomSheetOpen = false
            },
            onDismissRequest = { isBottomSheetOpen = false },
            onSelectCategory = {
                viewModel.onSelectCategory(it)
                isBottomSheetOpen = false
            },
            onSelectFavoriteCategory = viewModel::onSelectFavoriteCategory,
            modifyListVisible = state.value.modifyMyCategoryListVisibility
        )
    }
}

@Composable
fun CategorySelectButton(
    modifier: Modifier = Modifier,
    selectedCategory: Category?,
    onSelectCategory: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.noRippleClickable { onSelectCategory() }
    ) {
        Text(
            text = selectedCategory?.name ?: stringResource(id = string.home_all_category),
            fontSize = 26.sp,
            fontWeight = FontWeight.W700
        )
        Image(painter = painterResource(id = R.drawable.ic_category), contentDescription = null)
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}