package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccessTokenUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<String> = repository.getAccessToken()
}