package com.example.sachosaeng.data.model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class BookmarkListRequest(
    val voteBookmarkIds: List<Int>
)
