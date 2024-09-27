package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.core.domain.constant.OAuthType
import com.example.sachosaeng.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentAuthTypeUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<OAuthType> = repository.getRecentOauthType()
}