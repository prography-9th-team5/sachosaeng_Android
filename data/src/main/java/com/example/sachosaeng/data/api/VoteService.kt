package com.sachosaeng.app.data.api

import com.example.sachosaeng.data.model.vote.AddVoteRequest
import com.sachosaeng.app.data.model.BaseResponse
import com.sachosaeng.app.data.model.vote.VoteDetailInfoResponse
import com.sachosaeng.app.data.model.vote.VoteInfoResponse
import com.sachosaeng.app.data.model.vote.VoteListInfoByCategoryResponse
import com.sachosaeng.app.data.model.vote.VoteListInfoResponse
import com.sachosaeng.app.data.model.vote.VoteOptionRequest
import com.sachosaeng.app.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface VoteService {
    @GET("/api/v1/votes/daily")
    suspend fun getDailyVote(): ApiResult<BaseResponse<VoteInfoResponse>>

    @GET("/api/v1/votes/hot")
    suspend fun getHotVote(): ApiResult<BaseResponse<VoteListInfoResponse>>

    @GET("/api/v1/votes/hot/categories/{categoryId}")
    suspend fun getHotVoteByCategory(
        @Path("categoryId") categoryId: Int
    ): ApiResult<BaseResponse<VoteListInfoByCategoryResponse>>

    @GET("/api/v1/votes/hot/categories/{categoryId}")
    suspend fun getVotesByCategory(
        @Path("categoryId") categoryId: Int
    ): ApiResult<BaseResponse<VoteListInfoResponse>>

    @GET("/api/v1/votes/{voteId}")
    suspend fun getVote(
        @Path("voteId") voteId: Int,
        @Query("category-id") categoryId: Int? = null,
    ): ApiResult<BaseResponse<VoteDetailInfoResponse>>

    @PUT("/api/v1/votes/{voteId}/choices")
    suspend fun setVote(
        @Path("voteId") voteId: Int,
        @Body chosenVoteOptionIds: VoteOptionRequest
    ): ApiResult<BaseResponse<Unit>>

    @POST("/api/v1/votes")
    suspend fun addVote(
        @Body vote: AddVoteRequest
    ): ApiResult<BaseResponse<Unit>>
}