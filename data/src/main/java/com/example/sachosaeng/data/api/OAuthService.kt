package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.auth.TokenResponse
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.Header
import retrofit2.http.POST

interface OAuthService {
    @POST("/api/v1/auth/refresh")
    suspend fun getNewAccessToken(
        @Header("X-Device") device: String,
        @Header("Cookie") cookie: String,
    ): ApiResult<BaseResponse<TokenResponse>>
}