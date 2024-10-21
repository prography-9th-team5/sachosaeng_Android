package com.sachosaeng.app.data.api

import com.sachosaeng.app.data.model.BaseResponse
import com.sachosaeng.app.data.model.article.SimilarArticleDetailResponse
import com.sachosaeng.app.data.model.article.SimilarArticleResponse
import com.sachosaeng.app.data.model.bookmark.SingleArticleBookmarkRequest
import com.sachosaeng.app.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleService {
    @GET("/api/v1/similar-information")
    suspend fun getSimilarArticle(
        @Query("category-id") categoryId: Int,
        @Query("vote-id") voteId: Int,
        @Query("size") size: Int
    ): ApiResult<BaseResponse<SimilarArticleResponse>>

    @GET("/api/v1/information/{informationId}")
    suspend fun getArticleDetail(
        @Path("informationId") informationId: Int,
        @Query("category-id") categoryId: Int?,
    ): ApiResult<BaseResponse<SimilarArticleDetailResponse>>
}