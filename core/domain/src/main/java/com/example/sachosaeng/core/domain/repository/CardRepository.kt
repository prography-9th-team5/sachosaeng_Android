package com.example.sachosaeng.core.domain.repository

import com.example.sachosaeng.core.domain.model.Card


interface CardRepository {
    suspend fun getCardList(): List<Card>
    suspend fun getMyCardList(): List<Card>
    suspend fun bookmarkCard(card: Card)
}