package com.example.sachosaeng.domain.usecase.user

import User
import com.example.sachosaeng.domain.repository.UserRepository
import com.example.sachosaeng.domain.usecase.Usecase

class GetMyInfoUsecase(private val repository: com.example.sachosaeng.domain.repository.UserRepository) :
    com.example.sachosaeng.domain.usecase.Usecase<User> {
    override suspend fun invoke() = repository.getMyInfo()
}