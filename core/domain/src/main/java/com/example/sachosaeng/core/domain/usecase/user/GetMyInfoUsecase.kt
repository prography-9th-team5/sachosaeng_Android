package com.example.sachosaeng.core.domain.usecase.user

import com.example.sachosaeng.core.domain.model.User
import com.example.sachosaeng.core.domain.repository.UserRepository
import com.example.sachosaeng.core.domain.usecase.Usecase

class GetMyInfoUsecase(private val repository: UserRepository) : Usecase<User> {
    override suspend fun invoke() = repository.getMyInfo()
}