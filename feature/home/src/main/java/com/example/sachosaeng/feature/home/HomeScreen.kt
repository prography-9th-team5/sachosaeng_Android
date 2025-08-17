package com.example.sachosaeng.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color.Companion.White
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
import com.example.sachosaeng.feature.home.component.GrowthSystemDialog
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.ui.R.drawable
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.core.ui.component.SelectCategoryBottomSheet
import com.sachosaeng.app.core.ui.component.topappbar.SachosaengTopAppBar
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.util.constant.IntConstant.ALL_CATEGORY_ID
import com.sachosaeng.app.feature.home.component.ListByCategory
import com.sachosaeng.app.feature.home.component.MainList
import com.example.sachosaeng.feature.home.component.TodaysVoteDialog
import com.sachosaeng.app.feature.home.HomeScreenUiState
import com.sachosaeng.app.feature.home.HomeSideEffect
import com.sachosaeng.app.feature.home.HomeViewModel
import com.sachosaeng.app.feature.home.R
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit = {},
    navigateToAddVote: () -> Unit = {},
    navigateToVoteCard: (Int, Boolean) -> Unit = { _, _ -> },
    navigateToMyPage: () -> Unit = {},
    showLevelUpTooltip: () -> Unit = {},
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
            is HomeSideEffect.NavigateToMyPage -> navigateToMyPage()
            is HomeSideEffect.ShowLevelUpTooltip -> showLevelUpTooltip()
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
        navigateToSearch = navigateToSearch,
        onDailyVoteDialogConfirmClicked = viewModel::onDailyVoteDialogConfirmClicked,
        onGrowthSystemConfirmClicked = viewModel::onGrowthSystemConfirmClicked,
        dismissGrowthSystem = viewModel::dismissGrowthSystemConfirm
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
    navigateToSearch: () -> Unit,
    navigateToAddVote: () -> Unit,
    onDailyVoteDialogConfirmClicked: () -> Unit,
    onGrowthSystemConfirmClicked: () -> Unit,
    dismissGrowthSystem: () -> Unit,
) {
    val listState = rememberLazyListState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    if (state.isDailyVoteDialogOpen) TodaysVoteDialog(
        onClick = {
            onDailyVoteDialogConfirmClicked()
        }
    )
    if (!state.isGrowthSystemConfirmed) GrowthSystemDialog(
        onStartClick = {
            onGrowthSystemConfirmClicked()
        },
        onDismissClick = {
            dismissGrowthSystem()
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
    Scaffold(
        topBar = {
            SachosaengTopAppBar(
                modifier = modifier
                    .background(Gs_G2)
                    .padding(20.dp),
                componentRow = {
                    CategorySelectButton(
                        selectedCategory = state.selectedCategory,
                        onSelectCategory = { isBottomSheetOpen = true }
                    )
                    SearchButton(
                        modifier = modifier,
                        onClick = {
                            navigateToSearch()
                        }
                    )
                }
            )
        },
        content = { padding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Gs_G2)
                    .padding(horizontal = 20.dp)
            ) {
                val scope = rememberCoroutineScope()
                if (state.selectedCategory.id == ALL_CATEGORY_ID) MainList(
                    modifier = modifier.padding(padding),
                    state = state,
                    listState = listState,
                    navigateToVoteCard = navigateToVoteCard
                )
                else ListByCategory(
                    modifier = modifier.padding(padding),
                    state = state,
                    navigateToVoteCard = navigateToVoteCard
                )
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
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
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
    )
}

@Composable
private fun SearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = White)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .noRippleClickable {
                onClick()
            }
    ) {
        Text(
            color = Gs_G5,
            text = stringResource(id = string.search_button_label),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
        Image(
            painter = painterResource(id = drawable.ic_search),
            contentDescription = null,
            modifier = modifier.noRippleClickable { onClick() }
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
            .padding(20.dp)
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
    ) {
        (if (selectedCategory?.name?.isNotEmpty() == true) selectedCategory.name else stringResource(
            id = string.home_my_all_category
        )).let {
            Text(
                text = it,
                fontSize = 26.sp,
                fontWeight = FontWeight.W700
            )
        }
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
            isGrowthSystemConfirmed = false
        ),
        navigateToSearch = {},
        deleteWarningDialogMessage = {},
        onSelectFavoriteCategory = {},
        onSelectCategory = {},
        onModifyComplete = {},
        onModifyMyCategory = {},
        onAddVoteButtonClicked = {},
        navigateToVoteCard = { _, _ -> },
        navigateToAddVote = {},
        onGrowthSystemConfirmClicked = {},
        dismissGrowthSystem = {},
        onDailyVoteDialogConfirmClicked = {}
    )
}