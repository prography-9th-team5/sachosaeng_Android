package com.sachosaeng.app.core.usecase.auth

import com.sachosaeng.app.core.usecase.NoParameterUseCase
import com.sachosaeng.app.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmailUsecase @Inject constructor(
    private val repository: AuthRepository
) : NoParameterUseCase<Flow<String>> {
    override fun invoke(): Flow<String> = repository.getEmail()
}