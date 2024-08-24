package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.data.repository.card.CardRepository


class GetMyVoteListUsecase(private val repository: CardRepository) : NoParameterUseCase<List<Vote>> {
    override operator fun invoke() = repository.getMyCardList()
}