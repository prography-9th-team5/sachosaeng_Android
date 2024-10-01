package com.sachosaeng.app.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.VoteInfo
import com.sachosaeng.app.core.model.VoteList
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.VoteColumnByCategory
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.util.extension.StringExtension.toColorResource
import com.sachosaeng.app.feature.home.HomeScreenUiState

@Composable
fun ListByCategory(
    state: HomeScreenUiState,
    navigateToVoteCard: (Int, Boolean) -> Unit
) {
    LazyColumn {
        item {
            state.voteListWithCategory?.category.let {
                CategoryTitleCard(
                    category = state.voteListWithCategory!!.category,
                    date = state.hotVotes.description
                )
            }
        }
        item {
            if (state.hotVotes.voteInfo.isNotEmpty()) {
                VoteColumnByCategory(
                    modifier = Modifier.padding(top = 12.dp),
                    rankinTextVisibility = true,
                    voteList = state.hotVotes.voteInfo,
                    onVoteClick = { navigateToVoteCard(it, false) }
                )
            }
        }
        item {
            if (state.voteListWithCategory?.voteInfo?.isNotEmpty() == true) {
                Text(
                    modifier = Modifier.padding(top = 36.dp, bottom = 14.dp),
                    text = stringResource(id = R.string.sort_by_latest),
                    color = Gs_Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                VoteColumnByCategory(
                    voteList = state.voteListWithCategory.voteInfo,
                    onVoteClick = { navigateToVoteCard(it, false) }
                )
            }
        }
    }
}

@Composable
private fun CategoryTitleCard(
    modifier: Modifier = Modifier,
    category: Category,
    date: String = ""
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(category.color.toColorResource()))
            .padding(vertical = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = modifier
                    .padding(end = 12.dp)
                    .size(32.dp),
                model = if (category.imageUrl.isNullOrEmpty()) R.drawable.ic_hot_vote else category.imageUrl,
                contentDescription = "",
            )
            Text(
                text = date,
                color = Gs_Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700
            )
        }
    }
}

@Preview
@Composable
fun ListByCategoryPreview() {
    ListByCategory(
        state = HomeScreenUiState(
            voteListWithCategory = VoteList(
                category = Category(
                    name = "카테고리",
                    textColor = "000000"
                ),
                description = "2021.10.10",
                voteInfo = listOf(
                    VoteInfo(
                        id = 1,
                        title = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
                        category = Category(
                            name = "카테고리",
                            textColor = "000000",
                            color = "FF0000",
                            imageUrl = "",
                        ),
                        isClosed = false,
                        isVoted = false
                    ),
                    VoteInfo(
                        id = 1,
                        title = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
                        category = Category(
                            name = "카테고리",
                            textColor = "000000",
                            color = "FF0000",
                            imageUrl = "",
                        ),
                        isClosed = false,
                        isVoted = false
                    ),
                    VoteInfo(
                        id = 1,
                        title = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
                        category = Category(
                            name = "카테고리",
                            textColor = "000000",
                            color = "FF0000",
                            imageUrl = "",
                        ),
                        isClosed = false,
                        isVoted = false
                    )
                )
            )
        ), navigateToVoteCard = { _, _ -> }
    )
}