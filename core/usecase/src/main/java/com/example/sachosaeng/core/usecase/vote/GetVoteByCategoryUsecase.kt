package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.data.repository.vote.VoteRepository
import javax.inject.Inject

class GetVoteByCategoryUsecase @Inject constructor(private val repository: VoteRepository) {
    operator fun invoke(id: Int) = repository.getVotesByCategory(3)
}