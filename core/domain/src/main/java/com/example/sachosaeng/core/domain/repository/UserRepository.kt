package com.example.sachosaeng.core.domain.repository

import com.example.sachosaeng.core.domain.model.User

interface UserRepository {
    suspend fun getMyInfo(): User
    fun login(id: String, password: String)
    fun logout()
    fun signIn(user: User)
    fun updateMyInfo(user: User)
    suspend fun withdraw()
}