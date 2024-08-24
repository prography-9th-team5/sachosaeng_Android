package com.example.sachosaeng.data.repository.user

import com.example.sachosaeng.core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun setUserType(type: String): Flow<Boolean>
    fun getUserType(): Flow<String>

    fun getMyInfo(): User
    fun login(token: String)
    fun logout()
    fun signIn(user: User)
    fun updateMyInfo(user: User)
    fun withdraw()
}