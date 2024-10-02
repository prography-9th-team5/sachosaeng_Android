package com.sachosaeng.app.core.usecase.user

import com.sachosaeng.app.data.repository.user.UserRepository
import javax.inject.Inject

class GetMyInfoUsecase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke() = repository.getMyInfo()
}