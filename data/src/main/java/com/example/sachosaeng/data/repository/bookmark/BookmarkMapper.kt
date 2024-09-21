package com.example.sachosaeng.data.repository.bookmark

import com.example.sachosaeng.core.model.Bookmark
import com.example.sachosaeng.data.model.bookmark.BookmarkListRequest
import com.example.sachosaeng.data.model.bookmark.BookmarkResponse
import com.example.sachosaeng.data.model.bookmark.BookmarkedArticleResponse
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
                bookmarkId = it.voteBookmarkId,
                id = it.voteId,
                title = it.title,
                description = it.description
            )
        }
    }

    fun BookmarkedArticleResponse.toDomain(): List<Bookmark> {
        return information.map {
            Bookmark(
                bookmarkId = it.informationBookmarkId,
                id = it.informationId,
                title = it.title,
                description = ""
            )
        }
    }
}