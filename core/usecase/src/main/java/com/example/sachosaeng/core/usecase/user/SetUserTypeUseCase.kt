package com.sachosaeng.app.core.usecase.user

import com.sachosaeng.app.data.repository.user.UserRepository
import javax.inject.Inject

class SetUserTypeUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(type: String) = userRepository.setUserType(type)
}