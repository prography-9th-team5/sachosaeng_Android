package com.example.sachosaeng.data.repository.auth

import com.example.sachosaeng.data.api.AuthService
import com.example.sachosaeng.data.datasource.datastore.AuthDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val authLocalDataSource: AuthDataStore
) : AuthRepository {
    override fun login(id: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun join(email: String): Flow<Unit> =
        flow { emit(authService.join(email).getOrThrow().data) }

    override suspend fun withdraw() {
        TODO("Not yet implemented")
    }
}