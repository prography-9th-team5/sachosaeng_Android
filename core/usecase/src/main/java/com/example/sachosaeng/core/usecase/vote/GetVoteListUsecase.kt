package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.data.repository.vote.VoteRepository

class GetVoteListUsecase(private val repository: VoteRepository) {
    operator fun invoke() = repository.getVotesByCategory(3)
}