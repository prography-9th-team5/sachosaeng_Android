package com.sachosaeng.app.data.repository.bookmark

import com.sachosaeng.app.core.model.Bookmark
import com.sachosaeng.app.core.model.BookmarkType
import com.sachosaeng.app.data.model.bookmark.ArticleBookmarkListRequest
import com.sachosaeng.app.data.model.bookmark.BookmarkListRequest
import com.sachosaeng.app.data.model.bookmark.BookmarkResponse
import com.sachosaeng.app.data.model.bookmark.BookmarkedArticleResponse
import com.sachosaeng.app.data.model.bookmark.SingleVoteBookmarkRequest

object BookmarkMapper {
    fun List<Bookmark>.toRequest(): BookmarkListRequest {
        return BookmarkListRequest(
            voteBookmarkIds = this.map { it.bookmarkId },
        )
    }

    fun List<Bookmark>.toArticleRequest(): ArticleBookmarkListRequest {
        return ArticleBookmarkListRequest(
            informationBookmarkIds = this.map { it.bookmarkId },
        )
    }

    fun Int.toRequest(): SingleVoteBookmarkRequest {
        return SingleVoteBookmarkRequest(this)
    }

    fun BookmarkResponse.toDomain(): List<Bookmark> {
        return votes.map {
            Bookmark(
                type = BookmarkType.VOTE,
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
                type = BookmarkType.INFORMATION,
                bookmarkId = it.informationBookmarkId,
                id = it.informationId,
                title = it.title,
                description = ""
            )
        }
    }
}