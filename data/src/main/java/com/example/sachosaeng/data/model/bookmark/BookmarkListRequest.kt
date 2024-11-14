package com.sachosaeng.app.data.model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class BookmarkListRequest(
    val voteBookmarkIds: List<Int>
)

@Serializable
data class ArticleBookmarkListRequest(
    val informationBookmarkIds: List<Int>,
)

