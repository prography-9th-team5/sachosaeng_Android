package com.example.sachosaeng.data.repository.card

import com.example.sachosaeng.core.domain.model.Vote


class CardRepositoryImpl: CardRepository {
    override suspend fun getCardList(): List<Vote> {
        TODO("Not yet implemented")
    }

    override suspend fun getMyCardList(): List<Vote> {
        TODO("Not yet implemented")
    }

    override suspend fun bookmarkCard(vote: Vote) {
        TODO("Not yet implemented")
    }
}