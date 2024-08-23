package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository

class LoginUsecase(private val repository: UserRepository, val id: String, val password: String) :
    Usecase<Unit> {
    override suspend fun invoke() = repository.login(id = id, password = password)
}