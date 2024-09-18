package com.example.sachosaeng.data.repository.vote

import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.model.VoteInfo
import com.example.sachosaeng.core.model.VoteList
import kotlinx.coroutines.flow.Flow

interface VoteRepository {
    fun getDailyVote(): Flow<VoteInfo?>
    fun getHotVotes(): Flow<VoteList?>
    fun getVotesByCategory(categoryId: Int): Flow<VoteList?>
    fun getVote(voteId: Int): Flow<Vote?>
    fun setVote(voteId: Int, optionIds: List<Int?>): Flow<Unit>
}