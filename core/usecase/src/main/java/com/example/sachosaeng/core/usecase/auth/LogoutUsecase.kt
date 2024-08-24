package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository

class LogoutUsecase(private val repository: UserRepository) : Usecase<Nothing, Unit> {
    override fun invoke(param: Nothing) {
        TODO("Not yet implemented")
    }
}