package com.example.sachosaeng.data.repository.auth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(id: String, password: String)
    fun logout()
    fun join(email: String, userType: String): Flow<Boolean>
    fun getEmail(): Flow<String>
    suspend fun withdraw()
}