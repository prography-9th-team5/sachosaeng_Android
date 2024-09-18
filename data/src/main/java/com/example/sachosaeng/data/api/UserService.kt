package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.user.UserInfoResponse
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.GET

interface UserService {
    @GET("/api/v1/users")
    suspend fun getUserInfo(): ApiResult<BaseResponse<UserInfoResponse>>
}