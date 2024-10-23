package com.sachosaeng.app.data.repository.auth

import com.sachosaeng.app.core.domain.constant.OAuthType
import com.sachosaeng.app.data.api.AuthService
import com.sachosaeng.app.data.datasource.datastore.AuthDataStore
import com.sachosaeng.app.data.model.auth.JoinRequest
import com.sachosaeng.app.data.model.auth.LoginRequest
import com.sachosaeng.app.data.model.auth.LoginResponse
import com.sachosaeng.app.data.remote.util.onFailure
import com.sachosaeng.app.data.remote.util.onSuccess
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

    override fun logout(): Flow<Boolean> = flow {
        emit(authLocalDataSource.clearUserInfo())
    }

    override suspend fun getEmail(): String = authLocalDataSource.getEmail()

    override fun getRecentOauthType(): Flow<OAuthType> =
        flow { emit(authLocalDataSource.getRecentOauthType()) }

    override suspend fun setEmail(email: String, type: OAuthType): Boolean =
        authLocalDataSource.setEmail(email, type)

    override fun join(email: String, userType: String): Flow<Boolean> =
        flow {
            authService.join(
                joinRequest = JoinRequest(
                    email = email,
                    userType = userType
                )
            ).getOrThrow()?.data?.let { data ->
                emit(authLocalDataSource.setAccessToken(data.loginToken))
            } ?: emit(false)
        }
}
