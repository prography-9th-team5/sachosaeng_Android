package com.example.sachosaeng.data.repository.user

import android.graphics.Bitmap
import com.sachosaeng.app.core.model.User
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
    fun downloadProfileImage(bitmap: Bitmap): Flow<Boolean>
    fun getGrowthSystemConfirmed(): Flow<Boolean>
    fun setGrowthSystemConfirmed(growthSystemConfirmed: Boolean): Flow<Unit>
    fun setLevelUpNotification(isNeeded: Boolean): Flow<Unit>
    fun getLevelUpNotification(): Flow<Boolean>
    suspend fun setFcmToken(token: String)
    suspend fun testPushByUser(title: String, message: String)
    suspend fun testPushByToken(title: String, message: String)
}