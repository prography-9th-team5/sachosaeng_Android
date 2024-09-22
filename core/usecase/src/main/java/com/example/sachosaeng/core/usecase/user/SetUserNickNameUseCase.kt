package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.data.repository.user.UserRepository
import javax.inject.Inject

class SetUserNickNameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(nickName: String) = userRepository.setUserNickname(nickName)
}