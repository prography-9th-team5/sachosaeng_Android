package com.example.sachosaeng.data.repository.vote

import com.example.sachosaeng.data.api.VoteService
import com.example.sachosaeng.data.repository.vote.VoteMapper.toDomain
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VoteRepositoryImpl @Inject constructor(
    private val voteService: VoteService
) : VoteRepository {
    override fun getDailyVote() =
        flow { emit(voteService.getDailyVote().getOrThrow().data?.toDomain()) }

    override fun getHotVotes() = flow {
        emit(voteService.getHotVote().getOrThrow().data?.toDomain())
    }

    override fun getVotesByCategory(categoryId: Int) = flow {
        emit(voteService.getVotesByCategory(categoryId).getOrThrow().data?.toDomain())
    }
}