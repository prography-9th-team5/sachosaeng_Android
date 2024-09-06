package com.example.sachosaeng.data.remote.oauth

import com.example.sachosaeng.data.api.OAuthService
import com.example.sachosaeng.data.datasource.datastore.AuthDataStore
import com.example.sachosaeng.data.model.auth.TokenResponse
import com.example.sachosaeng.data.remote.util.onFailure
import com.example.sachosaeng.data.remote.util.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OAuthRepositoryImpl @Inject constructor(
 //   private val authService: OAuthService,
    private val authDataStore: AuthDataStore
) : OAuthRepository {
    override fun getNewAccessToken(): Flow<String> {
        return flow {
            authDataStore.getRefreshToken()
                .run {
//                    authService.getNewAccessToken().onSuccess { response ->
//                        response.data?.let { data -> setToken(data) }
//                            .run { emit(authDataStore.getAccessToken()) }
//                    }.onFailure {
//                        emit(authDataStore.getAccessToken())
//                    }
                }
        }
    }

    override fun getAccessToken(): Flow<String> =
        flow { emit(authDataStore.getAccessToken()) }

    private fun setToken(data: TokenResponse): Flow<Unit> {
        return flow {
            with(authDataStore) {
                setAccessToken(data.accessToken)
                setRefreshToken(data.refreshToken)
            }
        }
    }
}