package com.sachosaeng.app.data.api

import com.sachosaeng.app.data.model.BaseResponse
import com.sachosaeng.app.data.model.auth.LoginRequest
import com.sachosaeng.app.data.model.auth.TokenResponse
import com.sachosaeng.app.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OAuthService {
    @POST("/api/v1/auth/refresh")
    suspend fun getNewAccessToken(
        @Header("X-Device") device: String,
        @Header("Cookie") cookie: String,
    ): ApiResult<BaseResponse<TokenResponse>>

    @POST("/api/v1/auth/login")
    suspend fun loginWithEmail(
        @Body email: LoginRequest,
    ): ApiResult<BaseResponse<TokenResponse>>
}