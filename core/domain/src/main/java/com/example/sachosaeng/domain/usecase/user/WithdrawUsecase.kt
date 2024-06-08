package com.example.sachosaeng.domain.usecase.user

import com.example.sachosaeng.domain.repository.UserRepository
import com.example.sachosaeng.domain.usecase.Usecase

class WithdrawUsecase(private val repositroy : com.example.sachosaeng.domain.repository.UserRepository) :
    com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repositroy.withdraw()
}