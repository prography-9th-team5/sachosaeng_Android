package com.example.sachosaeng.data.model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class BookmarkedArticleResponse(
    val information: List<BookmarkedArticle>,
    val hasNext: Boolean,
    val nextCursor: Int?,
)

@Serializable
data class BookmarkedArticle(
    val informationBookmarkId: Int,
    val informationId: Int,
    val title: String,
)