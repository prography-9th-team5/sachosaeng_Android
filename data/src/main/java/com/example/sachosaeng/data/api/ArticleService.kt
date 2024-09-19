package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.article.SimilarArticleResponse
import com.example.sachosaeng.data.model.auth.JoinRequest
import com.example.sachosaeng.data.model.auth.LoginRequest
import com.example.sachosaeng.data.model.auth.LoginResponse
import com.example.sachosaeng.data.model.auth.TokenResponse
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ArticleService {
    @POST("/api/v1/similar-information")
    suspend fun getSimilarArticle(
        @Query("category-id") categoryId: Int,
        @Query("vote-id") voteId: Int,
        @Query("size") size: Int
    ): ApiResult<BaseResponse<SimilarArticleResponse>>
}