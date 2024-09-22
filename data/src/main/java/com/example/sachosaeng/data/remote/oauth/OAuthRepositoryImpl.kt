package com.example.sachosaeng.data.remote.oauth

import android.util.Log
import com.example.sachosaeng.core.util.manager.PackageManager
import com.example.sachosaeng.data.api.OAuthService
import com.example.sachosaeng.data.datasource.datastore.AuthDataStore
import com.example.sachosaeng.data.model.auth.TokenResponse
import com.example.sachosaeng.data.remote.util.onFailure
import com.example.sachosaeng.data.remote.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
    private val oAuthService: OAuthService,
    private val authDataStore: AuthDataStore,
    private val packageManager: PackageManager
) : OAuthRepository {

    override suspend fun getAccessToken(): String = authDataStore.getAccessToken()

    override suspend fun getRefreshToken(): String = authDataStore.getRefreshToken()

    override suspend fun refreshAccessToken() {
        oAuthService.getNewAccessToken(
            packageManager.getDeviceId(),
            "Refresh=${authDataStore.getRefreshToken()}"
        )
            .onSuccess { it.data?.let { it1 -> setToken(it1) } }
            .onFailure { setToken(TokenResponse("", "")) }
    }

    private fun setToken(data: TokenResponse): Flow<Unit> {
        return flow {
            with(authDataStore) {
                setAccessToken(data.accessToken)
                setRefreshToken(data.refreshToken)
            }
        }
    }
}