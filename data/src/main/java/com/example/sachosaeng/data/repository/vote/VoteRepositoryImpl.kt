package com.sachosaeng.app.data.repository.vote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sachosaeng.data.model.vote.AddVoteRequest
import com.example.sachosaeng.data.model.vote.SuggestedVote
import com.example.sachosaeng.data.repository.vote.VoteHistoryPagingSource
import com.sachosaeng.app.core.model.SuggestedVoteInfo
import com.sachosaeng.app.core.model.Vote
import com.sachosaeng.app.data.api.VoteService
import com.sachosaeng.app.data.model.vote.VoteOptionRequest
import com.sachosaeng.app.data.repository.vote.VoteMapper.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VoteRepositoryImpl @Inject constructor(
    private val voteService: VoteService
) : VoteRepository {
    override fun getDailyVote() =
        flow { emit(voteService.getDailyVote().getOrNull()?.data?.toDomain()) }

    override fun getHotVotes(categoryId: Int?) = flow {
        if (categoryId == null)
            emit(voteService.getHotVote().getOrNull()?.data?.toDomain())
        else
            emit(voteService.getHotVoteByCategory(categoryId).getOrNull()?.data?.toDomain())
    }

    override fun getVotesByCategory(categoryId: Int) = flow {
        emit(voteService.getVotesByCategory(categoryId).getOrNull()?.data?.toDomain())
    }

    override fun getVote(voteId: Int): Flow<Vote?> = flow {
        voteService.getVote(voteId = voteId).getOrNull()?.data?.toDomain()?.let {
            emit(it)
        }
    }

    override fun setVote(voteId: Int, optionIds: List<Int?>): Flow<Unit> = flow {
        voteService.setVote(voteId = voteId, VoteOptionRequest(chosenVoteOptionIds = optionIds))
            .getOrNull()?.data?.let { emit(Unit) }
    }

    override fun addVote(
        title: String,
        isMultipleChoiceAllowed: Boolean,
        options: List<String>,
        categoryId: Int
    ): Flow<Unit> = flow {
        voteService.addVote(
            AddVoteRequest(
                title = title,
                isMultipleChoiceAllowed = isMultipleChoiceAllowed,
                voteOptions = options,
                categoryIds = listOf(categoryId)
            )
        ).getOrNull()?.data?.let {
            emit(
                it
            )
        }
    }

    override fun getHistoryOfSuggestedVote(): Flow<PagingData<SuggestedVoteInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                VoteHistoryPagingSource(
                    voteService = voteService
                )
            }
        ).flow.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}