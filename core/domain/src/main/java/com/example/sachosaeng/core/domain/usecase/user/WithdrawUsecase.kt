package com.example.sachosaeng.core.domain.usecase.user

import com.example.sachosaeng.core.domain.repository.UserRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class WithdrawUsecase(private val repositroy : UserRepository) : Usecase<Unit> {
    override suspend fun invoke() = repositroy.withdraw()
}