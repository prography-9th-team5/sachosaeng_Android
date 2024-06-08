package com.example.sachosaeng.domain.usecase.card

import Card
import com.example.sachosaeng.domain.repository.CardRepository
import com.example.sachosaeng.domain.usecase.Usecase

class BookmarkCardUsecase(private val repository: com.example.sachosaeng.domain.repository.CardRepository, val card: Card) :
    com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.bookmarkCard(card = card)
}