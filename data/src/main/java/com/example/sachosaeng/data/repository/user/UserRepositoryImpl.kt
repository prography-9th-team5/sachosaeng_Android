package com.sachosaeng.app.data.repository.user

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
    private val userService: UserService
) : UserRepository {
    override fun setUserType(type: String) = flow { emit(userDataStore.setUserType(type)) }
    override fun getUserType() = flow { emit(userDataStore.getUserType()) }
    override fun getMyInfo(): Flow<User> =
        flow { userService.getUserInfo().getOrThrow().data?.toDomain()?.let { emit(it) } }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun signIn(user: User) {
        TODO("Not yet implemented")
    }

    override fun updateMyInfo(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun setUserNickname(nickname: String) {
        userService.updateUserNickname(
            nickname = NicknameRequest(
                nickname = nickname
            )
        )
    }

    override suspend fun setUserTypeToRemote(type: String) {
        userService.updateUserType(
            UserTypeRequest(
                userType = type
            )
        )
    }

    override fun withdraw(reason: String) = flow<Unit> {
        userService.withdraw(
            reason = WithdrawRequest(
                reason = reason
            )
        ).getOrThrow().data?.let { emit(it) }
    }
}