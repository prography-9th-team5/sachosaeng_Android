package com.sachosaeng.app.data.datasource.datastore

import com.sachosaeng.app.core.domain.constant.OAuthType

interface AuthDataStore {
    suspend fun setUserId(userId: Int): Boolean
    suspend fun getUserId(): Int
    suspend fun setEmail(email: String, type: OAuthType): Boolean
    suspend fun clearUserInfo(): Boolean
    suspend fun getEmail(): String
    suspend fun getRecentOauthType(): OAuthType
    suspend fun setAccessToken(token: String?): Boolean
    suspend fun getAccessToken(): String
    suspend fun getKakaoAccessToken(): String
    suspend fun setRefreshToken(token: String?): Boolean
    suspend fun getRefreshToken(): String
    suspend fun setKakaoLoginToken(token: String): Boolean
}