package com.sachosaeng.app.core.usecase.user

import com.sachosaeng.app.core.model.User
import com.sachosaeng.app.core.usecase.Usecase
import com.sachosaeng.app.data.repository.user.UserRepository

class UpdateMyInfoUsecase(private val repository: UserRepository): Usecase<User, Unit> {
    override fun invoke(user: User) {
        repository.updateMyInfo(user = user)
    }
}