package com.example.sachosaeng.core.domain.usecase.user

import com.example.sachosaeng.core.domain.repository.UserRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class LoginUsecase(private val repository: UserRepository, val id: String, val password: String) : Usecase<Unit> {
    override suspend fun invoke() = repository.login(id = id, password = password)
}