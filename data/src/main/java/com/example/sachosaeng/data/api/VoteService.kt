package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.auth.JoinRequest
import com.example.sachosaeng.data.model.auth.LoginRequest
import com.example.sachosaeng.data.model.auth.LoginResponse
import com.example.sachosaeng.data.model.vote.VoteInfoResponse
import com.example.sachosaeng.data.model.vote.VoteListInfoResponse
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VoteService {
    @GET("/api/v1/votes/daily")
    suspend fun getDailyVote(): ApiResult<BaseResponse<VoteInfoResponse>>

    @GET("/api/v1/votes/hot")
    suspend fun getHotVote(): ApiResult<BaseResponse<VoteListInfoResponse>>

    @GET("/api/v1/votes/hot/categories/{categoryId}")
    suspend fun getVotesByCategory(
        @Path("categoryId") categoryId: Int
    ): ApiResult<BaseResponse<VoteListInfoResponse>>
}