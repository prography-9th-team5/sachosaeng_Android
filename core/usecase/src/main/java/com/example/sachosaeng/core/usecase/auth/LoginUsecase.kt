package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository

class LoginUsecase(private val repository: UserRepository, val token: String) : Usecase<String, Unit> {
    override fun invoke(token: String) = repository.login(token = token)
}