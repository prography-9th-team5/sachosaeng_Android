package com.example.sachosaeng.core.domain.usecase.vote

import com.example.sachosaeng.core.domain.model.Vote
import com.example.sachosaeng.core.domain.repository.CardRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class GetVoteListUsecase(private val repository: CardRepository) : Usecase<List<Vote>> {
    override suspend fun invoke() = repository.getCardList()
}