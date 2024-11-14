package com.sachosaeng.app.core.usecase.vote

import com.sachosaeng.app.data.repository.vote.VoteRepository
import javax.inject.Inject

class SetVoteUseCase @Inject constructor(
    private val voteRepository: VoteRepository
) {
    operator fun invoke(voteId: Int, optionId: List<Int?>) = voteRepository.setVote(voteId, optionId)
}