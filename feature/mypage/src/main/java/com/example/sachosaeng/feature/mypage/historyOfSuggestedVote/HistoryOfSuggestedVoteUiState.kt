package com.example.sachosaeng.feature.mypage.historyOfSuggestedVote

import androidx.paging.PagingData
import com.sachosaeng.app.core.model.SuggestedVoteInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HistoryOfSuggestedVoteUiState(
    val voteList: Flow<PagingData<SuggestedVoteInfo>> = emptyFlow(),
)
