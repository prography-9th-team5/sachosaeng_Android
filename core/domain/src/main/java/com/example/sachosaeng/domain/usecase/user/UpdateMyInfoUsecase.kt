package com.example.sachosaeng.domain.usecase.user

import User
import com.example.sachosaeng.domain.repository.UserRepository
import com.example.sachosaeng.domain.usecase.Usecase

class UpdateMyInfoUsecase(private val repository: com.example.sachosaeng.domain.repository.UserRepository, val user: User):
    com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.updateMyInfo(user = user)
}