package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository

class LogoutUsecase(private val repository: UserRepository) : Usecase<Unit> {
    override suspend fun invoke() = repository.logout()
}