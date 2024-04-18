package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import com.example.domain.usecase.Usecase

class LogoutUsecase(private val repository: UserRepository) : Usecase<Unit> {
    override suspend fun invoke() = repository.logout()
}