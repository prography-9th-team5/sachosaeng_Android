package com.sachosaeng.app.data.api

import com.sachosaeng.app.data.model.BaseResponse
import com.sachosaeng.app.data.model.bookmark.BookmarkListRequest
import com.sachosaeng.app.data.model.bookmark.BookmarkResponse
import com.sachosaeng.app.data.model.bookmark.BookmarkedArticleResponse
import com.sachosaeng.app.data.model.bookmark.SingleArticleBookmarkRequest
import com.sachosaeng.app.data.model.bookmark.SingleVoteBookmarkRequest
import com.sachosaeng.app.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path

interface BookmarkService {
    @POST("/api/v1/bookmarks/votes/delete")
    suspend fun deleteBookmarks(
        @Body bookmarkIds: BookmarkListRequest
    ): ApiResult<BaseResponse<Unit>>

    @DELETE("/api/v1/bookmarks/votes/{voteId}")
    suspend fun deleteBookmark(
        @Path("voteId") voteId: Int
    ): ApiResult<BaseResponse<Unit>>

    @DELETE("/api/v1/bookmarks/information/{informationId}")
    suspend fun deleteArticleBookmark(
        @Path("informationId") informationId: Int
    ): ApiResult<BaseResponse<Unit>>

    @POST("/api/v1/bookmarks/votes")
    suspend fun voteBookmark(
        @Body voteId: SingleVoteBookmarkRequest
    ): ApiResult<BaseResponse<Unit>>

    @GET("/api/v1/bookmarks/votes/categories/{categoryId}")
    suspend fun getBookmarkList(
        @Path("categoryId") categoryId: Int
    ): ApiResult<BaseResponse<BookmarkResponse>>

    @GET("/api/v1/bookmarks/votes")
    suspend fun getAllBookmarkList(): ApiResult<BaseResponse<BookmarkResponse>>

    @GET("/api/v1/bookmarks/information")
    suspend fun getAllBookmarkedArticleList(): ApiResult<BaseResponse<BookmarkedArticleResponse>>

    @GET("/api/v1/bookmarks/information/categories/{categoryId}")
    suspend fun getBookmarkedArticleList(
        @Path("categoryId") categoryId: Int
    ): ApiResult<BaseResponse<BookmarkedArticleResponse>>

    @POST("/api/v1/bookmarks/information")
    suspend fun bookmarkArticle(
        @Body articleId: SingleArticleBookmarkRequest
    ): ApiResult<BaseResponse<Unit>>
}