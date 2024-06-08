package com.example.sachosaeng.domain.usecase.user

import User
import com.example.sachosaeng.domain.repository.UserRepository
import com.example.sachosaeng.domain.usecase.Usecase

class SignInUsecase(private val repository: com.example.sachosaeng.domain.repository.UserRepository, val user: User) :
    com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.signIn(user = user)
}