package com.example.sachosaeng.data.remote.oauth

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
    private val oAuthRepository: OAuthRepository,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.priorResponseCount() >= 2 || response.code != HTTP_UNAUTHORIZED) {
            return null
        }

        return runBlocking {
            oAuthRepository.refreshAccessToken()
            val newToken = oAuthRepository.getAccessToken()
            if (newToken.isNotEmpty()) {
                response.request.newBuilder()
                    .putTokenHeader(newToken)
            } else {
                null
            }
        }
    }

    private fun Response.priorResponseCount(): Int {
        var count = 0
        var prior = priorResponse
        while (prior != null) {
            count++
            prior = prior.priorResponse
        }
        return count
    }

    private fun Request.Builder.putTokenHeader(accessToken: String): Request {
        return this.addHeader(AUTHORIZATION, "Bearer $accessToken")
            .build()
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val LOCATION = "Location"
    }
}
