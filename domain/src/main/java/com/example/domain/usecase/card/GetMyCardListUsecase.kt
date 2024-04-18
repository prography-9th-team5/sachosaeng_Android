package com.example.domain.usecase.card

import Card
import com.example.domain.repository.CardRepository
import com.example.domain.usecase.Usecase

class GetMyCardListUsecase(private val repository: CardRepository) : Usecase<List<Card>> {
    override suspend fun invoke() = repository.getMyCardList()
}