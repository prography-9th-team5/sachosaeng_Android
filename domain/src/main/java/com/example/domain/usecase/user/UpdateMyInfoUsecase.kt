package com.example.domain.usecase.user

import User
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.Usecase

class UpdateMyInfoUsecase(private val repository: UserRepository, val user: User): Usecase<Unit> {
    override suspend fun invoke() = repository.updateMyInfo(user = user)
}