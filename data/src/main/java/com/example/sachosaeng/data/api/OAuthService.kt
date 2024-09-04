package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.auth.JoinRequest
import com.example.sachosaeng.data.model.auth.LoginRequest
import com.example.sachosaeng.data.model.auth.LoginResponse
import com.example.sachosaeng.data.model.auth.TokenResponse
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.POST

interface OAuthService {
    @POST("/api/v1/auth/refresh")
    suspend fun getNewAccessToken(): ApiResult<BaseResponse<TokenResponse>>
}