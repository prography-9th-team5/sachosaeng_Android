package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.data.repository.user.UserRepository
import javax.inject.Inject

class GetMyInfoUsecase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke() = repository.getMyInfo()
}