package com.sachosaeng.app.data.api

import com.sachosaeng.app.data.model.BaseResponse
import com.sachosaeng.app.data.model.auth.JoinRequest
import com.sachosaeng.app.data.model.auth.JoinResponse
import com.sachosaeng.app.data.model.auth.LoginRequest
import com.sachosaeng.app.data.model.auth.LoginResponse
import com.sachosaeng.app.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/join")
    suspend fun join(
        @Body joinRequest: JoinRequest
    ): ApiResult<BaseResponse<JoinResponse>>

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): ApiResult<BaseResponse<LoginResponse>>
}