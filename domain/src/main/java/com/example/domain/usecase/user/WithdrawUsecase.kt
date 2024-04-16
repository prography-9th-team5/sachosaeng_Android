package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import com.example.domain.usecase.Usecase

class WithdrawUsecase(private val repositroy : UserRepository) : Usecase<Unit> {
    override suspend fun invoke() = repositroy.withdraw()
}