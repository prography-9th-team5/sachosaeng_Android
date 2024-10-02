package com.sachosaeng.app.data.model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class SingleVoteBookmarkRequest(
    val voteId: Int
)
