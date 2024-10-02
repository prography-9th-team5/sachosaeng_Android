package com.sachosaeng.app.core.usecase.vote

import com.sachosaeng.app.data.repository.vote.VoteRepository
import javax.inject.Inject

class GetSingleVoteUsecase @Inject constructor(
    private val voteRepository: VoteRepository
) {
    operator fun invoke(voteId: Int) = voteRepository.getVote(voteId = voteId)
}