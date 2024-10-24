package com.sachosaeng.app.core.usecase.vote

import com.sachosaeng.app.data.repository.vote.VoteRepository

class GetVoteListUsecase(private val repository: VoteRepository) {
    operator fun invoke() = repository.getVotesByCategory(3)
}