package com.example.sachosaeng.data.repository.vote

import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.data.api.VoteService
import com.example.sachosaeng.data.model.vote.VoteOptionRequest
import com.example.sachosaeng.data.repository.vote.VoteMapper.toDomain
import kotlinx.coroutines.flow.Flow
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

    override fun getVote(voteId: Int): Flow<Vote?> = flow {
        voteService.getVote(voteId = voteId).getOrThrow().data?.toDomain()?.let {
            emit(it)
        }
    }

    override fun setVote(voteId: Int, optionIds: List<Int?>): Flow<Unit> = flow {
        voteService.setVote(voteId = voteId, VoteOptionRequest(chosenVoteOptionIds = optionIds))
            .getOrThrow().data?.let { emit(Unit) }
    }
}