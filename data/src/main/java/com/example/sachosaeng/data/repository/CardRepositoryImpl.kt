package com.example.sachosaeng.data.repository

import Card
import com.example.sachosaeng.domain.repository.CardRepository

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