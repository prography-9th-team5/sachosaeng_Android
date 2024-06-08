package com.example.sachosaeng.domain.usecase.user

import com.example.sachosaeng.domain.repository.UserRepository
import com.example.sachosaeng.domain.usecase.Usecase

class LogoutUsecase(private val repository: com.example.sachosaeng.domain.repository.UserRepository) :
    com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.logout()
}