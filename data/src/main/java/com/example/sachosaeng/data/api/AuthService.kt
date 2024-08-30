package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.auth.JoinRequest
import com.example.sachosaeng.data.model.auth.LoginRequest
import com.example.sachosaeng.data.model.auth.LoginResponse
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/join")
    suspend fun join(
        @Body joinRequest: JoinRequest
    ): ApiResult<BaseResponse<Unit>>

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): ApiResult<BaseResponse<LoginResponse>>
}