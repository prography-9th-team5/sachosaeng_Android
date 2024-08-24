package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.usecase.NoParameterUseCase

class GetSingleVoteUsecase : NoParameterUseCase<Vote> {
    override operator fun invoke() = Vote()
}