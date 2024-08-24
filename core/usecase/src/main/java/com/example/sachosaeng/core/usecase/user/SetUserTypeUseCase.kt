package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetUserTypeUseCase @Inject constructor(
    private val userRepository: UserRepository
): Usecase<String, Flow<Boolean>> {
    override operator fun invoke(type: String) = userRepository.setUserType(type)
}