package com.sachosaeng.app.data.remote.oauth

interface OAuthRepository {
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun getNewAccessToken(): String?
}