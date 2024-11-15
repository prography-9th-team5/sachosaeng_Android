package com.example.sachosaeng.core.usecase.vote

import com.sachosaeng.app.data.repository.vote.VoteRepository
import javax.inject.Inject

class GetHistoryOfSuggestedVoteUseCase @Inject constructor(
    private val voteRepository: VoteRepository
) {
    operator fun invoke() = voteRepository.getHistoryOfSuggestedVote()
}