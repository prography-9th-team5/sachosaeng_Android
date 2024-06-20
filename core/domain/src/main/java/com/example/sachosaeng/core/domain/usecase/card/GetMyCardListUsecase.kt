package com.example.sachosaeng.core.domain.usecase.card

import com.example.sachosaeng.core.domain.model.Card
import com.example.sachosaeng.core.domain.repository.CardRepository


class GetMyCardListUsecase(private val repository: CardRepository) :
    com.example.sachosaeng.core.domain.usecase.Usecase<List<Card>> {
    override suspend fun invoke() = repository.getMyCardList()
}