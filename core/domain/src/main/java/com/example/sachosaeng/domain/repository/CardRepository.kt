package com.example.sachosaeng.domain.repository

import Card

interface CardRepository {
    suspend fun getCardList(): List<Card>
    suspend fun getMyCardList(): List<Card>
    suspend fun bookmarkCard(card: Card)
}