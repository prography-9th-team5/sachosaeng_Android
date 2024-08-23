package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.core.domain.model.User
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository

class UpdateMyInfoUsecase(private val repository: UserRepository, val user: User): Usecase<Unit> {
    override suspend fun invoke() = repository.updateMyInfo(user = user)
}