package com.example.sachosaeng.data.remote.oauth

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthenticator @Inject constructor() : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val newToken = try {
           //토큰 새로 얻기
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return response.request.newBuilder()
            .header(OAuthConstants.AUTHORIZATION, "${OAuthConstants.AUTHORIZATION_BEARER} $newToken")
            .build()
    }
}

private object OAuthConstants {
    const val AUTHORIZATION = "Authorization"
    const val AUTHORIZATION_BEARER = "Bearer"
}