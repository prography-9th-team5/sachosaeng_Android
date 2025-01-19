package com.sachosaeng.app.feature.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.component.dialog.WarningDialog
import com.example.sachosaeng.core.util.FirebaseUtil
import com.example.sachosaeng.core.util.FirebaseUtil.SCREEN_NAME_HOME
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R.drawable
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.core.ui.component.SelectCategoryBottomSheet
import com.sachosaeng.app.core.ui.component.topappbar.SachosaengTopAppBar
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.util.constant.IntConstant.ALL_CATEGORY_ID
import com.sachosaeng.app.feature.home.component.ListByCategory
import com.sachosaeng.app.feature.home.component.MainList
import com.sachosaeng.app.feature.home.component.TodaysVoteDialog
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(
    navigateToAddVote: () -> Unit = {},
    navigateToVoteCard: (Int, Boolean) -> Unit = { _, _ -> },
    viewModel: HomeViewModel = hiltViewModel()
) {
    var isWarningDialogMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        FirebaseUtil.setScreenView(SCREEN_NAME_HOME)
    }

    val state = viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.NavigateToVoteDetail -> navigateToVoteCard(it.voteId, it.isDailyVote)
            is HomeSideEffect.NavigateToAddVote -> navigateToAddVote()
            is HomeSideEffect.ShowDialog -> isWarningDialogMessage = it.message
            else -> {}
        }
    }

    HomeScreen(
        state = state.value,
        deleteWarningDialogMessage = { isWarningDialogMessage = "" },
        isWarningDialogMessage = isWarningDialogMessage,
        onSelectFavoriteCategory = viewModel::onSelectFavoriteCategory,
        onSelectCategory = viewModel::onSelectCategory,
        onModifyComplete = viewModel::onModifyComplete,
        onModifyMyCategory = viewModel::onModifyMyCategory,
        onAddVoteButtonClicked = viewModel::onAddVoteButtonClicked,
        navigateToVoteCard = { voteId, isDailyVote -> navigateToVoteCard(voteId, isDailyVote) },
        navigateToAddVote = navigateToAddVote,
        onDailyVoteDialogConfirmClicked = viewModel::onDailyVoteDialogConfirmClicked
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreenUiState,
    deleteWarningDialogMessage: () -> Unit,
    isWarningDialogMessage: String = "",
    onSelectFavoriteCategory: (Category) -> Unit,
    onSelectCategory: (Category) -> Unit,
    onModifyComplete: () -> Unit,
    onModifyMyCategory: () -> Unit,
    onAddVoteButtonClicked: () -> Unit,
    navigateToVoteCard: (Int, Boolean) -> Unit,
    navigateToAddVote: () -> Unit,
    onDailyVoteDialogConfirmClicked: () -> Unit,
) {
    val listState = rememberLazyListState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    if (state.isDailyVoteDialogOpen) TodaysVoteDialog(
        onClick = {
            onDailyVoteDialogConfirmClicked()
        }
    )
    if (isWarningDialogMessage.isNotEmpty()) WarningDialog(
        onConfirm = {
            navigateToAddVote()
            deleteWarningDialogMessage()
        },
        errorMessage = isWarningDialogMessage,
        confirmLabel = stringResource(id = string.confirm_label)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Gs_G2)
            .padding(20.dp)
    ) {
        val scope = rememberCoroutineScope()

        Log.d("HomeScreen", "HomeScreen: ${state.allCategory}")
        Log.d("HomeScreen", "HomeScreen: ${state.selectedCategory}")

        Column {
            SachosaengTopAppBar(
                modifier = modifier,
                componentRow = {
                    CategorySelectButton(
                        selectedCategory = state.selectedCategory,
                        onSelectCategory = { isBottomSheetOpen = true }
                    )
                }
            )
            if (state.selectedCategory.id == ALL_CATEGORY_ID) MainList(
                state = state,
                listState = listState,
                navigateToVoteCard = navigateToVoteCard
            )
            else ListByCategory(
                state = state,
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
        AddVoteFab(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onAddVoteButtonClicked
        )
    }
    if (isBottomSheetOpen) {
        SelectCategoryBottomSheet(
            allCategoryList = state.allCategory,
            myCategoryList = state.myCategory,
            onModifyMyCategoryButtonClicked = onModifyMyCategory,
            onModifyComplete = {
                onModifyComplete()
                isBottomSheetOpen = false
            },
            onDismissRequest = { isBottomSheetOpen = false },
            onSelectCategory = {
                onSelectCategory(it)
                isBottomSheetOpen = false
            },
            onSelectFavoriteCategory = onSelectFavoriteCategory,
            modifyListVisible = state.modifyMyCategoryListVisibility
        )
    }
}

@Composable
fun AddVoteFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .noRippleClickable { onClick() }
            .background(color = Gs_G6)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Image(
            modifier = modifier.padding(end = 8.dp),
            painter = painterResource(id = drawable.ic_add_vote),
            contentDescription = null
        )
        Text(
            color = Gs_White,
            text = stringResource(id = string.add_vote_title),
            fontSize = 16.sp,
            fontWeight = FontWeight.W700
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
        modifier = modifier
            .noRippleClickable { onSelectCategory() }
            .padding(bottom = 20.dp)
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
    HomeScreen(
        state = HomeScreenUiState(
            allCategory = listOf(
                Category(1, "category1"),
                Category(2, "category2"),
                Category(3, "category3"),
            ),
            myCategory = listOf(
                Category(1, "category1"),
                Category(2, "category2"),
                Category(3, "category3"),
            ),
            selectedCategory = Category(1, "category1"),
            modifyMyCategoryListVisibility = false,
            isDailyVoteDialogOpen = false,
        ),
        deleteWarningDialogMessage = {},
        onSelectFavoriteCategory = {},
        onSelectCategory = {},
        onModifyComplete = {},
        onModifyMyCategory = {},
        onAddVoteButtonClicked = {},
        navigateToVoteCard = { _, _ -> },
        navigateToAddVote = {},
    ) {
    }
}