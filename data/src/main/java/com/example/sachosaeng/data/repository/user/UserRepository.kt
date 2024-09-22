package com.example.sachosaeng.data.repository.user

import com.example.sachosaeng.core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun setUserNickname(nickname: String)
    fun setUserType(type: String): Flow<Boolean>
    suspend fun setUserTypeToRemote(type: String)
    fun getUserType(): Flow<String>
    fun getMyInfo(): Flow<User>
    fun logout()
    fun signIn(user: User)
    fun updateMyInfo(user: User)
    fun withdraw(reason: String): Flow<Unit>
}