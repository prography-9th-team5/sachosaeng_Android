package com.example.sachosaeng.data.remote.oauth

import com.example.sachosaeng.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthenticator @Inject constructor(
//    private val oAuthRepository: OAuthRepository
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == HTTP_UNAUTHORIZED) {
            synchronized(this) {
                return runBlocking {
                    val newToken = "token"
                    return@runBlocking response.request.newBuilder()
                        .putTokenHeader(newToken)
                        .build()
                }
            }
        }
        return response.request
    }

    private fun Request.Builder.putTokenHeader(accessToken: String): Request.Builder {
        return this.header(AUTHORIZATION, "Bearer $accessToken")
    }

    companion object {
        private const val AUTHORIZATION = "authorization"
    }
}
