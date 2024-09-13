package com.example.sachosaeng.data.repository.bookmark

import com.example.sachosaeng.data.model.bookmark.BookmarkRequest
import com.example.sachosaeng.data.model.bookmark.SingleVoteBookmarkRequest

object BookmarkMapper {
    fun List<Int>.toRequest(): BookmarkRequest {
        return BookmarkRequest(this)
    }

    fun Int.toRequest(): SingleVoteBookmarkRequest {
        return SingleVoteBookmarkRequest(this)
    }
}