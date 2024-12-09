package com.sachosaeng.app.data.repository.vote

import androidx.paging.PagingData
import com.sachosaeng.app.core.model.SuggestedVoteInfo
import com.sachosaeng.app.core.model.Vote
import com.sachosaeng.app.core.model.VoteInfo
import com.sachosaeng.app.core.model.VoteList
import kotlinx.coroutines.flow.Flow

interface VoteRepository {
    fun getDailyVote(): Flow<VoteInfo?>
    fun getHotVotes(categoryId: Int? = null): Flow<VoteList?>
    fun getVotesByCategory(categoryId: Int): Flow<VoteList?>
    fun getVote(voteId: Int): Flow<Vote?>
    fun setVote(voteId: Int, optionIds: List<Int?>): Flow<Unit>
    fun addVote(
        title: String,
        isMultipleChoiceAllowed: Boolean,
        options: List<String>,
        categoryId: Int
    ): Flow<Int>
    fun getHistoryOfSuggestedVote(): Flow<PagingData<SuggestedVoteInfo>>
}