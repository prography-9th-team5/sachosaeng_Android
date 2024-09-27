package com.example.sachosaeng.data.repository.auth

import com.example.sachosaeng.core.domain.constant.OAuthType
import com.example.sachosaeng.data.api.AuthService
import com.example.sachosaeng.data.datasource.datastore.AuthDataStore
import com.example.sachosaeng.data.model.auth.JoinRequest
import com.example.sachosaeng.data.model.auth.LoginRequest
import com.example.sachosaeng.data.model.auth.LoginResponse
import com.example.sachosaeng.data.remote.util.onFailure
import com.example.sachosaeng.data.remote.util.onSuccess
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
            setUserId(data.userId)
            setAccessToken(data.accessToken)
            setRefreshToken(data.refreshToken)
        }
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun getEmail(): Flow<String> =
        flow { emit(authLocalDataSource.getEmail()) }

    override fun getRecentOauthType(): Flow<OAuthType> =
        flow { emit(authLocalDataSource.getRecentOauthType()) }

    override suspend fun setEmail(email: String, type: OAuthType): Boolean = authLocalDataSource.setEmail(email, type)

    override fun join(email: String, userType: String): Flow<Boolean> =
        flow {
            authService.join(
                joinRequest = JoinRequest(
                    email = email,
                    userType = userType
                )
            ).getOrThrow().data?.let { data ->
                authLocalDataSource.setAccessToken(data.loginToken)
            }.also {
                login(email).collect { emit(it) }
            }
        }
}
