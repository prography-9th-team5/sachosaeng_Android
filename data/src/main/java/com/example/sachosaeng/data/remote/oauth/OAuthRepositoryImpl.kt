package com.sachosaeng.app.data.remote.oauth

import com.sachosaeng.app.core.util.manager.DeviceManager
import com.sachosaeng.app.data.api.OAuthService
import com.sachosaeng.app.data.datasource.datastore.AuthDataStore
import com.sachosaeng.app.data.model.auth.LoginRequest
import com.sachosaeng.app.data.model.auth.TokenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val oAuthService: OAuthService,
    private val authDataStore: AuthDataStore,
    private val deviceManager: DeviceManager
) : OAuthRepository {
    override suspend fun getAccessToken(): String = authDataStore.getAccessToken()

    override suspend fun getRefreshToken(): String = authDataStore.getRefreshToken()

    override suspend fun getNewAccessToken(): String? {
        return runCatching {
            oAuthService.getNewAccessToken(
                deviceManager.getDeviceId(),
                "Refresh=${authDataStore.getRefreshToken()}"
            ).getOrThrow().data?.also { setToken(it) }?.accessToken
        }.getOrNull() ?: getNewTokenFromEmail()?.accessToken
    }

    private suspend fun getNewTokenFromEmail(): TokenResponse? {
        return runCatching {
            oAuthService.loginWithEmail(
                LoginRequest(email = authDataStore.getEmail())
            ).getOrThrow().data?.also { setToken(it) }
        }.getOrNull()
    }

    private suspend fun setToken(data: TokenResponse) {
        with(authDataStore) {
            setAccessToken(data.accessToken)
            setRefreshToken(data.refreshToken)
        }
    }
}