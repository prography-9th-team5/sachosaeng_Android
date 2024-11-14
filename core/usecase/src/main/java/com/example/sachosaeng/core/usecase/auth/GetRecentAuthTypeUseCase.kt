package com.sachosaeng.app.core.usecase.auth

import com.sachosaeng.app.core.domain.constant.OAuthType
import com.sachosaeng.app.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentAuthTypeUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<OAuthType> = repository.getRecentOauthType()
}