package com.example.sachosaeng.core.domain.usecase.vote

import com.example.sachosaeng.core.domain.model.Vote
import com.example.sachosaeng.core.domain.repository.CardRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class BookmarkVoteUsecase(private val repository: CardRepository, val vote: Vote) : Usecase<Unit> {
    override suspend fun invoke() = repository.bookmarkCard(vote = vote)
}