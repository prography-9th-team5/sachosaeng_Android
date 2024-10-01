package com.sachosaeng.app.data.repository.vote

import com.sachosaeng.app.core.model.Vote
import com.sachosaeng.app.data.api.VoteService
import com.sachosaeng.app.data.model.vote.VoteOptionRequest
import com.sachosaeng.app.data.repository.vote.VoteMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VoteRepositoryImpl @Inject constructor(
    private val voteService: VoteService
) : VoteRepository {
    override fun getDailyVote() =
        flow { emit(voteService.getDailyVote().getOrThrow().data?.toDomain()) }

    override fun getHotVotes(categoryId: Int?) = flow {
        if (categoryId == null)
            emit(voteService.getHotVote().getOrThrow().data?.toDomain())
        else
            emit(voteService.getHotVoteByCategory(categoryId).getOrThrow().data?.toDomain())
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