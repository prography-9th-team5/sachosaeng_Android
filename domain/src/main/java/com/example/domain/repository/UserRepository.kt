package com.example.domain.repository

import User

interface UserRepository {
    suspend fun getMyInfo(): User
    fun login(id: String, password: String)
    fun logout()
    fun signIn(user: User)
    fun updateMyInfo(user: User)
    suspend fun withdraw()
}