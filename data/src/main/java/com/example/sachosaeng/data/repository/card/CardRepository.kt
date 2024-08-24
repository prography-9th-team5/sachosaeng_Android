package com.example.sachosaeng.data.repository.card

import com.example.sachosaeng.core.model.Vote


interface CardRepository {
    fun getCardList(): List<Vote>
    fun getMyCardList(): List<Vote>
    fun bookmarkCard(vote: Vote)
}