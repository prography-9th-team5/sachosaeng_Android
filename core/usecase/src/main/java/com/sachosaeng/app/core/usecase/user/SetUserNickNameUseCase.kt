package com.sachosaeng.app.core.usecase.user

import com.sachosaeng.app.data.repository.user.UserRepository
import javax.inject.Inject

class SetUserNickNameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(nickName: String) = userRepository.setUserNickname(nickName)
}