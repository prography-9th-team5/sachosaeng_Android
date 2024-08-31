package com.example.sachosaeng.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import coil.compose.AsyncImage
import com.example.sachosaeng.core.model.VoteList
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.ui.component.CategoryTitleText
import com.example.sachosaeng.core.ui.component.VoteColumnByCategory
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_G2
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HomeScreen(
    moveToMyPage: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()
    val isSelectCategoryModalOpen = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Gs_G2)
    ) {
        Topbar(
            onCategorySelectButtonClicked = { isSelectCategoryModalOpen.value = true },
            userType = state.value.userType,
            onProfileImageClicked = {
                moveToMyPage()
            }
        )
        state.value.dailyVote?.title?.let { TodaysVoteCard(state.value.dailyVote!!.title) }
        VoteColumnListByCategory(state.value.hotVotes)
        VoteColumnListByCategory(state.value.voteList)
        if (isSelectCategoryModalOpen.value) {
            SelectCategoryBottomSheet(
                allCategoryList = state.value.allCategory,
                myCategoryList = state.value.myCategory,
                onDismissRequest = {
                    isSelectCategoryModalOpen.value = false
                }, onSelectCategory = {
                    isSelectCategoryModalOpen.value = false
                    viewModel.onSelectCategory(it)
                }
            )
        }
    }
}

@Composable
fun Topbar(
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
        CategorySelectButton(onSelectCategory = { onCategorySelectButtonClicked() })
        ProfileImage(userType, onClick = {
            onProfileImageClicked()
        })
    }
}

@Composable
fun CategorySelectButton(modifier: Modifier = Modifier, onSelectCategory: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.noRippleClickable { onSelectCategory() }
    ) {
        Text(
            text = stringResource(id = string.home_all_category),
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

@Composable
fun VoteColumnListByCategory(voteCardList: List<VoteList>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 32.dp)
    ) {
        items(voteCardList.size) {
            CategoryTitleText(category = voteCardList[it].category)
            VoteColumnByCategory(
                voteList = voteCardList[it].voteInfo
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}