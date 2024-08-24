package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.data.repository.card.CardRepository

class BookmarkVoteUsecase(private val repository: CardRepository, val vote: Vote) : NoParameterUseCase<Unit> {
    override operator fun invoke() = repository.bookmarkCard(vote = vote)
}