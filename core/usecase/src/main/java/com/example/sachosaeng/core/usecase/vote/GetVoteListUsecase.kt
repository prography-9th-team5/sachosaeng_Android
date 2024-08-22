package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.core.domain.model.Vote
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.card.CardRepository

class GetVoteListUsecase(private val repository: CardRepository) : Usecase<List<Vote>> {
    override suspend fun invoke() = repository.getCardList()
}