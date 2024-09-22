package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.data.repository.user.UserRepository
import javax.inject.Inject

class SetUserTypetoRemoteUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(type: String) = userRepository.setUserTypeToRemote(type)
}