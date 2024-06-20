package com.example.sachosaeng.core.domain.usecase.user

import com.example.sachosaeng.core.domain.repository.UserRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class LogoutUsecase(private val repository: UserRepository) : Usecase<Unit> {
    override suspend fun invoke() = repository.logout()
}