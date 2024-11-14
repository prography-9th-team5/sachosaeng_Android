package com.sachosaeng.app.data.model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class BookmarkResponse(
    val votes: List<Bookmark>
)

@Serializable
data class Bookmark(
    val voteBookmarkId: Int,
    val voteId: Int,
    val title: String,
    val description: String
)