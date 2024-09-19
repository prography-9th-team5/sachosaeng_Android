package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.data.repository.vote.VoteRepository


class GetMyVoteListUsecase(private val repository: VoteRepository) {
    operator fun invoke() = repository.getVote(3)
}