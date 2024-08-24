package com.example.sachosaeng.data.repository.card

import com.example.sachosaeng.core.domain.model.Vote


interface CardRepository {
    suspend fun getCardList(): List<Vote>
    suspend fun getMyCardList(): List<Vote>
    suspend fun bookmarkCard(vote: Vote)
}