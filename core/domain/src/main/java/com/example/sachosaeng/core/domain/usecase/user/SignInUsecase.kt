package com.example.sachosaeng.core.domain.usecase.user

import com.example.sachosaeng.core.domain.model.User
import com.example.sachosaeng.core.domain.repository.UserRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class SignInUsecase(private val repository: UserRepository, val user: User) : Usecase<Unit> {
    override suspend fun invoke() = repository.signIn(user = user)
}