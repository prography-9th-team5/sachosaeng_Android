package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.core.domain.model.Vote
import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.core.usecase.Usecase

class GetSingleVoteUsecase : NoParameterUseCase<Vote> {
    override operator fun invoke() = Vote()
}