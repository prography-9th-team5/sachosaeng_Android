package com.example.sachosaeng.core.usecase.auth

import com.example.sachosaeng.data.remote.oauth.OAuthRepository
import javax.inject.Inject

class GetAccessTokenUsecase @Inject constructor(
    private val repository: OAuthRepository
) {
    suspend operator fun invoke(): String = repository.getAccessToken()
}