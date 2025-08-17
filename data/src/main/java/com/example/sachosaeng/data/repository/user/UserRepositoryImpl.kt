package com.example.sachosaeng.data.repository.user

import android.graphics.Bitmap
import com.example.sachosaeng.data.local.manager.FileManager
import com.sachosaeng.app.core.model.User
import com.sachosaeng.app.data.api.UserService
import com.sachosaeng.app.data.datasource.datastore.UserDataStore
import com.sachosaeng.app.data.model.user.NicknameRequest
import com.sachosaeng.app.data.model.user.UserTypeRequest
import com.sachosaeng.app.data.model.user.WithdrawRequest
import com.sachosaeng.app.data.repository.user.UserMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore,
    private val userService: UserService,
    private val fileManager: FileManager
) : UserRepository {
    override fun setUserType(type: String) = flow { emit(userDataStore.setUserType(type)) }
    override fun getUserType() = flow { emit(userDataStore.getUserType()) }
    override fun getMyInfo(): Flow<User> =
        flow { userService.getUserInfo().getOrNull()?.data?.toDomain()?.let { emit(it) } }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun signIn(user: User) {
        TODO("Not yet implemented")
    }

    override fun updateMyInfo(user: User) {
        TODO("Not yet implemented")
    }

    override fun getGrowthSystemConfirmed(): Flow<Boolean> =
        flow { emit(userDataStore.getUserGrowthSystemConfirmed()) }

    override fun setGrowthSystemConfirmed(growthSystemConfirmed: Boolean): Flow<Unit> =
        flow {
            emit(userDataStore.setUserGrowthSystemConfirmed(growthSystemConfirmed))
        }

    override fun setLevelUpNotification(isNeeded: Boolean): Flow<Unit> =
        flow {
            emit(userDataStore.setLevelUpNotification(isNeeded))
        }

    override fun getLevelUpNotification(): Flow<Boolean> =
        flow {
            emit(userDataStore.getLevelUpNotification())
        }

    override suspend fun setUserNickname(nickname: String) {
        userService.updateUserNickname(
            nickname = NicknameRequest(
                nickname = nickname
            )
        ).also {
            userDataStore.setUserNickName(name = nickname)
        }
    }

    override suspend fun setUserTypeToRemote(type: String) {
        userService.updateUserType(
            UserTypeRequest(
                userType = type
            )
        )
    }

    override fun withdraw(reason: String) = flow {
        userService.withdraw(
            reason = WithdrawRequest(
                reason = reason
            )
        ).getOrNull()?.data?.let { emit(it) }
    }

    override fun downloadProfileImage(bitmap: Bitmap): Flow<Boolean> = flow {
        val fileName = userDataStore.getUserNickName()
        emit(fileManager.downloadImage(bitmap = bitmap, fileName = fileName).isSuccess)
    }
}