package com.example.sachosaeng.data.repository.auth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(id: String, password: String)
    fun logout()
    fun join(email: String): Flow<Unit>
    suspend fun withdraw()
}