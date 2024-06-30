package com.example.sachosaeng.data.repository

import com.example.sachosaeng.core.domain.model.Card
import com.example.sachosaeng.core.domain.repository.CardRepository


class CardRepositoryImpl: CardRepository {
    override suspend fun getCardList(): List<Card> {
        TODO("Not yet implemented")
    }

    override suspend fun getMyCardList(): List<Card> {
        TODO("Not yet implemented")
    }

    override suspend fun bookmarkCard(card: Card) {
        TODO("Not yet implemented")
    }
}