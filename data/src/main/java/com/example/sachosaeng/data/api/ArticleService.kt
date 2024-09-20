package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.article.SimilarArticleResponse
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("/api/v1/similar-information")
    suspend fun getSimilarArticle(
        @Query("category-id") categoryId: Int,
        @Query("vote-id") voteId: Int,
        @Query("size") size: Int
    ): ApiResult<BaseResponse<SimilarArticleResponse>>
}