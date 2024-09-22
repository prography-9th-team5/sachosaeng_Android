package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.user.NicknameRequest
import com.example.sachosaeng.data.model.user.UserInfoResponse
import com.example.sachosaeng.data.model.user.UserTypeRequest
import com.example.sachosaeng.data.model.user.WithdrawRequest
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
    @GET("/api/v1/users")
    suspend fun getUserInfo(): ApiResult<BaseResponse<UserInfoResponse>>

    @POST("/api/v1/auth/withdraw")
    suspend fun withdraw(
        @Body reason: WithdrawRequest
    ): ApiResult<BaseResponse<Unit>>

    @PUT("/api/v1/users/nickname")
    suspend fun updateUserNickname(
        @Body nickname: NicknameRequest
    ): ApiResult<BaseResponse<Unit>>

    @PUT("/api/v1/users/user-type")
    suspend fun updateUserType(
        @Body nickname: UserTypeRequest
    ): ApiResult<BaseResponse<Unit>>
}