package com.example.sachosaeng.data.model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class BookmarkRequest(
    val voteBookmarkIds: List<Int>
)
