package com.example.sachosaeng.data.remote.oauth

import com.example.sachosaeng.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class OAuthHeaderInterceptor @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val accessToken = oAuthRepository.getAccessToken().first()
            val request = if (accessToken.isNotEmpty()) {
                chain.request().putTokenHeader(accessToken)
            } else {
                chain.request()
            }
            chain.proceed(request)
        }
    }

    private fun Request.putTokenHeader(accessToken: String): Request {
        return this.newBuilder()
            .addHeader(AUTHORIZATION, "Bearer $accessToken")
            .build()
    }

    companion object {
        private const val AUTHORIZATION = "authorization"
    }
}