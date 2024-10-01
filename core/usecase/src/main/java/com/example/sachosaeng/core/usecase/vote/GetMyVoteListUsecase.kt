package com.sachosaeng.app.core.usecase.vote

import com.sachosaeng.app.data.repository.vote.VoteRepository


class GetMyVoteListUsecase(private val repository: VoteRepository) {
    operator fun invoke() = repository.getVote(3)
}