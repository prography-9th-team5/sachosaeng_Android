package com.example.sachosaeng.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.feature.R
import com.example.sachosaeng.feature.util.component.CircleCategoryButton
import com.example.sachosaeng.feature.util.component.TabRowComponent
import com.example.sachosaeng.feature.util.component.VoteCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.container.stateFlow.collectAsState()
    val isSelectCategoryModalOpen = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(Gs_G2)
    ) {
        Topbar(
            onCategorySelectButtonClicked = { isSelectCategoryModalOpen.value = true },
            profileImageUrl = state.value.profileImageUrl
        )
        VoteCardList(state.value.voteList)
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
fun Topbar(onCategorySelectButtonClicked: () -> Unit, profileImageUrl: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        CategorySelectButton(onSelectCategory = { onCategorySelectButtonClicked() })
        ProfileImage(profileImageUrl)
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
            text = stringResource(id = R.string.category),
            fontSize = 26.sp,
            fontWeight = FontWeight.W700
        )
        Card(
            colors = CardDefaults.cardColors().copy(
                containerColor = Gs_G3,
                contentColor = Gs_Black
            ),
        )
        {
            Text(
                text = stringResource(id = R.string.change),
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
            )
        }
    }
}

@Composable
fun ProfileImage(profileImageUrl: String) {
    AsyncImage(
        modifier = Modifier.size(40.dp),
        model = if (profileImageUrl != "") profileImageUrl else R.drawable.ic_home_default_profile,
        contentDescription = "",
    )
}

@Composable
fun VoteCardList(voteCardList: List<VoteList>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(voteCardList.size) {
            VoteCard(
                category = voteCardList[it].category,
                voteList = voteCardList[it].voteInfo
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
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
                stringResource(id = R.string.all_category)
            ),
            contentScreens = listOf(
                {
                    FlowRow(
                        modifier = Modifier.padding(top = 32.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        myCategoryList.forEach {
                            CircleCategoryButton(
                                category = it,
                                onClickCategory = { onSelectCategory(it) })
                        }
                    }
                },
                {
                    FlowRow(
                        modifier = Modifier.padding(top = 32.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        allCategoryList.forEach {
                            CircleCategoryButton(
                                category = it,
                                onClickCategory = { onSelectCategory(it) })
                        }
                    }
                }
            )
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}