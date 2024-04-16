package com.example.domain.usecase.user

import User
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.Usecase

class GetMyInfoUsecase(private val repository: UserRepository) : Usecase<User> {
    override suspend fun invoke() = repository.getMyInfo()
}