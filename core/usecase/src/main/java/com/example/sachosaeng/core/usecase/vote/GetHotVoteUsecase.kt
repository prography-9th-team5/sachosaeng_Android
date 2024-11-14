package com.sachosaeng.app.core.usecase.vote

import com.sachosaeng.app.data.repository.vote.VoteRepository
import javax.inject.Inject


class GetHotVoteUsecase @Inject constructor(private val repository: VoteRepository) {
    operator fun invoke(categoryId: Int? = null) = repository.getHotVotes(categoryId)
}