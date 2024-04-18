package com.example.domain.usecase.card

import Card
import com.example.domain.repository.CardRepository
import com.example.domain.usecase.Usecase

class BookmarkCardUsecase(private val repository: CardRepository, val card: Card) : Usecase<Unit> {
    override suspend fun invoke() = repository.bookmarkCard(card = card)
}