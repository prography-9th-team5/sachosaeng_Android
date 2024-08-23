package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.core.domain.model.User
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository

class GetMyInfoUsecase(private val repository: UserRepository) : Usecase<User> {
    override suspend fun invoke() = repository.getMyInfo()
}