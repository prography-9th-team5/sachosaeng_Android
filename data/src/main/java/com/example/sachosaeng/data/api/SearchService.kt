package com.sachosaeng.app.data.api

import com.sachosaeng.app.data.model.BaseResponse
import com.sachosaeng.app.data.model.vote.MultipleCategoryVoteListInfoResponse
import com.sachosaeng.app.data.model.vote.VoteInfoResponse
import com.sachosaeng.app.data.model.vote.VoteListInfoResponse
import com.sachosaeng.app.data.remote.util.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/api/v1/search")
    suspend fun getSearchResult(
        @Query("keyword") query: String
    ): ApiResult<BaseResponse<MultipleCategoryVoteListInfoResponse>>
}