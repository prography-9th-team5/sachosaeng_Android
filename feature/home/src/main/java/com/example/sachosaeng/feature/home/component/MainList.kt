package com.sachosaeng.app.feature.home.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import com.sachosaeng.app.core.ui.component.CategoryTitleText
import com.sachosaeng.app.core.ui.component.VoteCardByCategory
import com.sachosaeng.app.core.ui.component.VoteColumnByCategory
import com.sachosaeng.app.feature.home.HomeScreenUiState

@Composable
fun MainList(
    state: HomeScreenUiState,
    listState: LazyListState,
    navigateToVoteCard: (Int, Boolean) -> Unit
) {
    LazyColumn(
        state = listState
    ) {
        item {
            state.dailyVote?.title?.let {
                TodaysVoteCard(
                    voteTitle = state.dailyVote.title,
                    onClick = { state.dailyVote.id.let { navigateToVoteCard(it, true) } }
                )
            }
        }
        item {
            CategoryTitleText(category = state.hotVotes.category)
            VoteCardByCategory(
                rankinTextVisibility = true,
                voteList = state.hotVotes.voteInfo,
                onVoteClick = { navigateToVoteCard(it, false) }
            )
        }
        items(state.mainVoteList.size) {
            if (state.mainVoteList[it]?.voteInfo?.isNotEmpty() == true) {
                CategoryTitleText(
                    category = state.mainVoteList[it]!!.category
                )
                VoteColumnByCategory(
                    voteList = state.mainVoteList[it]!!.voteInfo,
                    onVoteClick = { navigateToVoteCard(it, false) }
                )
            }
        }
    }
}
