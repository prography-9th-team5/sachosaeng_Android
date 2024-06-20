package com.example.sachosaeng.core.domain.usecase.card

import com.example.sachosaeng.core.domain.model.Card
import com.example.sachosaeng.core.domain.repository.CardRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class BookmarkCardUsecase(private val repository: CardRepository, val card: Card) :
    Usecase<Unit> {
    override suspend fun invoke() = repository.bookmarkCard(card = card)
}