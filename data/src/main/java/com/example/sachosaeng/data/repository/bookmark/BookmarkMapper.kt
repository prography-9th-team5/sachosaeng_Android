package com.example.sachosaeng.data.repository.bookmark

import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.data.model.bookmark.BookmarkListRequest
import com.example.sachosaeng.data.model.bookmark.BookmarkResponse
import com.example.sachosaeng.data.model.bookmark.SingleVoteBookmarkRequest

object BookmarkMapper {
    fun List<Int>.toRequest(): BookmarkListRequest {
        return BookmarkListRequest(this)
    }

    fun Int.toRequest(): SingleVoteBookmarkRequest {
        return SingleVoteBookmarkRequest(this)
    }

    fun BookmarkResponse.toDomain(): List<Bookmark> {
        return votes.map {
            Bookmark(
                voteBookmarkId = it.voteBookmarkId,
                voteId = it.voteId,
                title = it.title,
                description = it.description
            )
        }
    }
}