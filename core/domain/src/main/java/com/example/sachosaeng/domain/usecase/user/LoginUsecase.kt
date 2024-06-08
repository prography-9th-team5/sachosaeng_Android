package com.example.sachosaeng.domain.usecase.user

import com.example.sachosaeng.domain.repository.UserRepository
import com.example.sachosaeng.domain.usecase.Usecase

class LoginUsecase(private val repository: com.example.sachosaeng.domain.repository.UserRepository, val id: String, val password: String) :
    com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.login(id = id, password = password)
}