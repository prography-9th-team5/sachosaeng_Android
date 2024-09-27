package com.example.sachosaeng.data.repository.auth

import com.example.sachosaeng.core.domain.constant.OAuthType
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String): Flow<Boolean>
    fun logout()
    fun join(email: String, userType: String): Flow<Boolean>
    fun getEmail(): Flow<String>
    fun getRecentOauthType(): Flow<OAuthType>
    suspend fun setEmail(email: String, type: OAuthType): Boolean
}