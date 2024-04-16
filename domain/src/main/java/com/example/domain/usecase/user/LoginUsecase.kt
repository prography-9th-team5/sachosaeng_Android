package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import com.example.domain.usecase.Usecase

class LoginUsecase(private val repository: UserRepository, val id: String, val password: String) : Usecase<Unit> {
    override suspend fun invoke() = repository.login(id = id, password = password)
}