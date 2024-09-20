package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.bookmark.BookmarkListRequest
import com.example.sachosaeng.data.model.bookmark.BookmarkResponse
import com.example.sachosaeng.data.model.bookmark.SingleVoteBookmarkRequest
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookmarkService {
    @DELETE("/api/v1/bookmarks/votes")
    suspend fun deleteBookmarks(
        @Body bookmarkIds: BookmarkListRequest
    ): ApiResult<BaseResponse<Unit>>

    @DELETE("/api/v1/bookmarks/votes/{voteId}")
    suspend fun deleteBookmark(
        @Path("voteId") voteId: Int
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
}