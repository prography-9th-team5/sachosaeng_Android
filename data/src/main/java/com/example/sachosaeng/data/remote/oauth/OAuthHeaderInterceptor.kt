package com.example.sachosaeng.data.remote.oauth

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class OAuthHeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {  }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}