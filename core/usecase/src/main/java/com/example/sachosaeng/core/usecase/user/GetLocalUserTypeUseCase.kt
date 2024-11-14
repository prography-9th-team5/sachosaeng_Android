package com.sachosaeng.app.core.usecase.user

import com.sachosaeng.app.core.usecase.NoParameterUseCase
import com.sachosaeng.app.core.usecase.Usecase
import com.sachosaeng.app.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserTypeUseCase @Inject constructor(
    private val userRepository: UserRepository
): NoParameterUseCase<Flow<String>> {
    override operator fun invoke() = userRepository.getUserType()
}