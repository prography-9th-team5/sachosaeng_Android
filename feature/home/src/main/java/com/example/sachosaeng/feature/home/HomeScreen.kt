package com.example.sachosaeng.feature.home

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.component.CategoryTitleText
import com.example.sachosaeng.core.ui.component.VoteColumnByCategory
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_G4
import com.example.sachosaeng.core.ui.theme.Gs_G6
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HomeScreen(
    moveToMyPage: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()
    val isSelectCategoryModalOpen = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.initHomeScreen()
    }

    Column(
        modifier = Modifier
            .background(Gs_G2)
    ) {
        Topbar(
            onCategorySelectButtonClicked = { isSelectCategoryModalOpen.value = true },
            profileImageUrl = state.value.profileImageUrl,
            onProfileImageClicked = {
                moveToMyPage()
            }
        )
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
    profileImageUrl: String,
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
        ProfileImage(profileImageUrl, onClick = {
            onProfileImageClicked()
        })
    }
}

@Composable
fun CategorySelectButton(onSelectCategory: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onSelectCategory() }
    ) {
        Text(
            text = stringResource(id = string.home_all_category),
            fontSize = 26.sp,
            fontWeight = FontWeight.W700
        )
        Card(
            border = BorderStroke(1.dp, Gs_G4),
            colors = CardDefaults.cardColors().copy(
                containerColor = Gs_G3,
                contentColor = Gs_Black
            ),
        )
        {
            Text(
                color = Gs_G6,
                text = stringResource(id = string.category_change),
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    }
}

@Composable
fun ProfileImage(profileImageUrl: String, onClick: () -> Unit = {}) {
    AsyncImage(
        modifier = Modifier
            .size(40.dp)
            .clickable { onClick() },
        model = if (profileImageUrl != "") profileImageUrl else R.drawable.splash_image,
        contentDescription = "",
    )
}

@Composable
fun VoteColumnListByCategory(voteCardList: List<VoteList>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
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