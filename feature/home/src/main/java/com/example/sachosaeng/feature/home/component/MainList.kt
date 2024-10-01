package com.example.sachosaeng.feature.home.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.sachosaeng.core.ui.component.CategoryTitleText
import com.example.sachosaeng.core.ui.component.VoteColumnByCategory
import com.example.sachosaeng.feature.home.HomeScreenUiState

@Composable
fun MainList(
    state: HomeScreenUiState,
    listState: androidx.compose.foundation.lazy.LazyListState,
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
            VoteColumnByCategory(
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