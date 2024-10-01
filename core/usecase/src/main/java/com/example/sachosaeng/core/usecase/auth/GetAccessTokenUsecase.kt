package com.sachosaeng.app.core.usecase.auth

import com.sachosaeng.app.data.remote.oauth.OAuthRepository
import javax.inject.Inject

class GetAccessTokenUsecase @Inject constructor(
    private val repository: OAuthRepository
) {
    suspend operator fun invoke(): String = repository.getAccessToken()
}