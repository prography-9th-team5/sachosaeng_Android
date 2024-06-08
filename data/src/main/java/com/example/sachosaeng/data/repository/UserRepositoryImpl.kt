package com.example.sachosaeng.data.repository

import User
import com.example.sachosaeng.domain.repository.UserRepository

class UserRepositoryImpl : com.example.sachosaeng.domain.repository.UserRepository {
    override suspend fun getMyInfo(): User {
        TODO("Not yet implemented")
    }

    override fun login(id: String, password: String) {
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

    override suspend fun withdraw() {
        TODO("Not yet implemented")
    }
}