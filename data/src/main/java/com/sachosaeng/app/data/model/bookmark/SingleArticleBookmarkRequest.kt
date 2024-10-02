package com.sachosaeng.app.data.model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class SingleArticleBookmarkRequest(
    val informationId: Int
)
