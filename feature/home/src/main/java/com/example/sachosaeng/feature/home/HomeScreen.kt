package com.example.sachosaeng.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.ui.component.CategoryTitleText
import com.example.sachosaeng.core.ui.component.VoteColumnByCategory
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_G2
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    moveToMyPage: () -> Unit = {},
    navigateToVoteCard: (Int) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    val state = viewModel.collectAsState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    Box {
        val scope = rememberCoroutineScope()
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Gs_G2)
        ) {
            Topbar(
                selectedCategory = state.value.selectedCategory,
                onCategorySelectButtonClicked = { isBottomSheetOpen = true },
                userType = state.value.userType,
                onProfileImageClicked = {
                    moveToMyPage()
                }
            )
            LazyColumn(
                state = listState,
                modifier = modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                item {
                    state.value.dailyVote?.title?.let {
                        TodaysVoteCard(
                            voteTitle = state.value.dailyVote!!.title,
                            onClick = { state.value.dailyVote?.id?.let { navigateToVoteCard(it) } }
                        )
                    }
                }
                item {
                    CategoryTitleText(category = state.value.hotVotes.category)
                    VoteColumnByCategory(voteList = state.value.hotVotes.voteInfo)
                }
                items(state.value.voteListWithCategory.size) {
                    CategoryTitleText(category = state.value.voteListWithCategory[it]!!.category)
                    VoteColumnByCategory(voteList = state.value.voteListWithCategory[it]!!.voteInfo)
                }
            }
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
fun Topbar(
    selectedCategory: Category?,
    onCategorySelectButtonClicked: () -> Unit,
    userType: UserType,
    onProfileImageClicked: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        CategorySelectButton(
            selectedCategory = selectedCategory,
            onSelectCategory = { onCategorySelectButtonClicked() })
        ProfileImage(userType, onClick = {
            onProfileImageClicked()
        })
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

@Composable
fun ProfileImage(userType: UserType, onClick: () -> Unit = {}) {
    Image(
        modifier = Modifier
            .size(40.dp)
            .clickable { onClick() }
            .clip(CircleShape),
        painter = painterResource(id = userType.userTypeImageRes),
        contentDescription = "",
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}