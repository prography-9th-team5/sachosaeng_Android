package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.core.usecase.NoParameterUseCase
import com.example.sachosaeng.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmailUsecase @Inject constructor(
    private val repository: AuthRepository
) : NoParameterUseCase<Flow<String>> {
    override fun invoke(): Flow<String> = repository.getEmail()
}