package com.example.sachosaeng.data.repository.user

import com.example.sachosaeng.core.model.User
import com.example.sachosaeng.data.datasource.datastore.UserDataStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : UserRepository {
    override fun setUserType(type: String) = flow { emit(userDataStore.setUserType(type)) }

    override fun getUserType() = flow { emit(userDataStore.getUserType()) }

    override fun getMyInfo(): User {
        TODO("Not yet implemented")
    }

    override fun login(token: String) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun signIn(user: User) {
        TODO("Not yet implemented")
    }

    override fun updateMyInfo(user: User) {
        TODO("Not yet implemented")
    }

    override fun withdraw() {
        TODO("Not yet implemented")
    }
}