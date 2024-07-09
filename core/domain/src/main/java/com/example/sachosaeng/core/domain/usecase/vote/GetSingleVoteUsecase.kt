package com.example.sachosaeng.core.domain.usecase.vote

import com.example.sachosaeng.core.domain.model.Vote
import com.example.sachosaeng.core.domain.usecase.Usecase

class GetSingleVoteUsecase : Usecase<Vote> {
    override suspend fun invoke() = Vote()
}