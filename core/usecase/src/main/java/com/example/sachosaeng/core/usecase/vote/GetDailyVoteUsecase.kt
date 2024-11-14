package com.sachosaeng.app.core.usecase.vote

import com.sachosaeng.app.data.repository.vote.VoteRepository
import javax.inject.Inject


class GetDailyVoteUsecase @Inject constructor(private val repository: VoteRepository) {
    operator fun invoke() = repository.getDailyVote()
}