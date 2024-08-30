package com.example.sachosaeng.core.usecase.user

import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserTypeUseCase @Inject constructor(
    private val userRepository: UserRepository
): NoParameterUseCase<Flow<String>> {
    override operator fun invoke() = userRepository.getUserType()
}