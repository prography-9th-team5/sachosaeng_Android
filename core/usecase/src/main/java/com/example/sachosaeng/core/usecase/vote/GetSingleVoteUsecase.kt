package com.example.sachosaeng.core.usecase.vote

import com.example.sachosaeng.core.domain.model.Vote
import com.example.sachosaeng.core.usecase.Usecase

class GetSingleVoteUsecase : Usecase<Vote> {
    override suspend fun invoke() = Vote()
}