package com.sachosaeng.app.data.api

import com.example.sachosaeng.data.model.user.UserFcmTokenRequest
import com.sachosaeng.app.data.model.BaseResponse
import com.sachosaeng.app.data.model.user.NicknameRequest
import com.sachosaeng.app.data.model.user.UserInfoResponse
import com.sachosaeng.app.data.model.user.UserTypeRequest
import com.sachosaeng.app.data.model.user.WithdrawRequest
import com.sachosaeng.app.data.remote.util.ApiResult
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

    @POST("/api/v1/push/token")
    suspend fun setFcmToken(
        @Body tokenRequest: UserFcmTokenRequest,
    ): ApiResult<BaseResponse<Unit>>
}