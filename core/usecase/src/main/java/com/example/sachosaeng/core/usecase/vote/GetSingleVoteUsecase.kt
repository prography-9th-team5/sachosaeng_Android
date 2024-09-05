package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.data.repository.vote.VoteRepository
import javax.inject.Inject

class GetSingleVoteUsecase @Inject constructor(
    private val voteRepository: VoteRepository
) {
    operator fun invoke(voteId: Int) = voteRepository.getVote(voteId = voteId)
}