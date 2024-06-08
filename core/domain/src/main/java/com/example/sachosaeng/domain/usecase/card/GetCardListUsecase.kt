package com.example.sachosaeng.domain.usecase.card

import Card
import com.example.sachosaeng.domain.repository.CardRepository
import com.example.sachosaeng.domain.usecase.Usecase

class GetCardListUsecase(private val repository: com.example.sachosaeng.domain.repository.CardRepository) :
    com.example.sachosaeng.domain.usecase.Usecase<List<Card>> {
    override suspend fun invoke() = repository.getCardList()
}