package com.sachosaeng.app.data.remote.oauth

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
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val accessToken = oAuthRepository.getAccessToken()

        var request = chain.request().let {
            if (accessToken.isNotEmpty()) it.putTokenHeader(accessToken) else it
        }

        var response = chain.proceed(request)
        repeat(2) {
            if (response.isRedirect) {
                val location = response.header(LOCATION) ?: return@runBlocking response
                request = request.newBuilder().url(location).build()
                response = chain.proceed(request)
            } else {
                return@runBlocking response
            }
        }
        response
    }

    private fun Request.putTokenHeader(accessToken: String): Request {
        return this.newBuilder()
            .addHeader(AUTHORIZATION, "Bearer $accessToken")
            .build()
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val LOCATION = "Location"
    }
}