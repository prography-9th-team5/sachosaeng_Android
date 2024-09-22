package com.example.sachosaeng.data.repository.user

import com.example.sachosaeng.core.model.User
import com.example.sachosaeng.data.api.UserService
import com.example.sachosaeng.data.datasource.datastore.UserDataStore
import com.example.sachosaeng.data.model.user.WithdrawRequest
import com.example.sachosaeng.data.repository.user.UserMapper.toDomain
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

    override fun withdraw(reason: String) = flow<Unit> {
        userService.withdraw(
            reason = WithdrawRequest(
                reason = reason
            )
        ).getOrThrow().data?.let { emit(it) }
    }
}