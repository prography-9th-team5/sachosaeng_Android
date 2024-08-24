package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.core.model.User
import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.data.repository.user.UserRepository

class GetMyInfoUsecase(private val repository: UserRepository) : NoParameterUseCase<User> {
    override fun invoke() = repository.getMyInfo()
}