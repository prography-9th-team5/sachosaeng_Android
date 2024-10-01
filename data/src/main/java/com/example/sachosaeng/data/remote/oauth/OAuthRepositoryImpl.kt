package com.sachosaeng.app.data.remote.oauth

import com.sachosaeng.app.core.util.manager.DeviceManager
import com.sachosaeng.app.data.api.OAuthService
import com.sachosaeng.app.data.datasource.datastore.AuthDataStore
import com.sachosaeng.app.data.model.auth.LoginRequest
import com.sachosaeng.app.data.model.auth.TokenResponse
import com.sachosaeng.app.data.remote.util.onFailure
import com.sachosaeng.app.data.remote.util.onSuccess
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val oAuthService: OAuthService,
    private val authDataStore: AuthDataStore,
    private val deviceManager: DeviceManager
) : OAuthRepository {
    override suspend fun getAccessToken(): String = authDataStore.getAccessToken()

    override suspend fun getRefreshToken(): String = authDataStore.getRefreshToken()

    override suspend fun refreshAccessToken() {
        oAuthService.getNewAccessToken(
            deviceManager.getDeviceId(),
            "Refresh=${authDataStore.getRefreshToken()}"
        )
            .onSuccess { it.data?.let { it1 -> setToken(it1) } }
            .onFailure {
                getNewAccessTokenFromEmail()?.let { it1 -> setToken(it1) }
                    ?: setToken(TokenResponse("", ""))
            }
    }

    private suspend fun getNewAccessTokenFromEmail(): TokenResponse? {
        return oAuthService.loginWithEmail(
            LoginRequest(
                email = authDataStore.getEmail()
            )
        ).getOrNull()?.data
    }

    private suspend fun setToken(data: TokenResponse) {
        with(authDataStore) {
            setAccessToken(data.accessToken)
            setRefreshToken(data.refreshToken)
        }
    }
}