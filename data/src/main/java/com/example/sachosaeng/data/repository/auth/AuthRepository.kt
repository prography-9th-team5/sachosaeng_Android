package com.sachosaeng.app.data.repository.auth

import com.sachosaeng.app.core.domain.constant.OAuthType
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String): Flow<Boolean>
    fun logout(): Flow<Boolean>
    fun join(email: String, userType: String): Flow<Boolean>
    suspend fun getEmail(): String
    fun getRecentOauthType(): Flow<OAuthType>
    suspend fun setEmail(email: String, type: OAuthType): Boolean
}