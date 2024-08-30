package com.example.sachosaeng.data.repository.auth

import com.example.sachosaeng.data.api.AuthService
import com.example.sachosaeng.data.datasource.datastore.AuthDataStore
import com.example.sachosaeng.data.model.auth.JoinRequest
import com.example.sachosaeng.data.model.auth.LoginRequest
import com.example.sachosaeng.data.model.auth.LoginResponse
import com.example.sachosaeng.data.remote.util.onFailure
import com.example.sachosaeng.data.remote.util.onSuccess
import com.example.sachosaeng.data.util.ERROR_CODE.SUCCESS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val authLocalDataSource: AuthDataStore
) : AuthRepository {
    override fun login(email: String): Flow<Boolean> = flow {
        authService.login(LoginRequest(email = email))
            .onSuccess { response ->
                response.data?.let { setUserInfo(it) }.run { emit(true) }
            }.onFailure {
                emit(false)
            }
    }

    private suspend fun setUserInfo(data: LoginResponse) {
        with(authLocalDataSource) {
            setEmail(data.userId)
            setAccessToken(data.accessToken)
            setRefreshToken(data.refreshToken)
        }
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun getEmail(): Flow<String> =
        flow { emit(authLocalDataSource.getEmail()) }

    override fun getAccessToken(): Flow<String> =
        flow { emit(authLocalDataSource.getAccessToken()) }

    override fun join(email: String, userType: String): Flow<Boolean> =
        flow {
            emit(
                authService.join(
                    joinRequest = JoinRequest(
                        email = email,
                        userType = userType
                    )
                ).getOrThrow().code == SUCCESS
            )
        }

    override suspend fun withdraw() {
        TODO("Not yet implemented")
    }
}
