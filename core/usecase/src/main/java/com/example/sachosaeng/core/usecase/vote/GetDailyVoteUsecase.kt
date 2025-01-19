package com.sachosaeng.app.core.usecase.vote

import com.sachosaeng.app.core.model.Vote
import com.sachosaeng.app.data.repository.vote.VoteRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class GetDailyVoteUsecase @Inject constructor(val repository: VoteRepository) {
    suspend operator fun invoke(): Vote? = repository.getDailyVote().first()?.let { vote ->
        repository.getVote(voteId = vote.id).first()
    }
}