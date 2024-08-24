package com.example.sachosaeng.data.repository.auth

import android.util.Log
import com.example.sachosaeng.data.api.AuthService
import com.example.sachosaeng.data.datasource.datastore.AuthDataStore
import com.example.sachosaeng.data.model.auth.JoinRequest
import com.example.sachosaeng.data.remote.util.onFailure
import com.example.sachosaeng.data.remote.util.onSuccess
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

    override fun getEmail(): Flow<String> =
        flow { emit(authLocalDataSource.getEmail()) }

    override fun join(email: String, userType: String): Flow<Unit> =
        flow {
            authService.join(
                joinRequest = JoinRequest(
                    email = email,
                    userType = userType
                )
            ).getOrThrow()
        }

    override suspend fun withdraw() {
        TODO("Not yet implemented")
    }
}