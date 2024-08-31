package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.data.repository.vote.VoteRepository
import javax.inject.Inject


class GetDailyVoteUsecase @Inject constructor(private val repository: VoteRepository) {
    operator fun invoke() = repository.getDailyVote()
}