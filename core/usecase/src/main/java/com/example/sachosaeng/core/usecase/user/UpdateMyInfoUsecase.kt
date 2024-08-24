package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.core.model.User
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository

class UpdateMyInfoUsecase(private val repository: UserRepository): Usecase<User, Unit> {
    override fun invoke(user: User) {
        repository.updateMyInfo(user = user)
    }
}