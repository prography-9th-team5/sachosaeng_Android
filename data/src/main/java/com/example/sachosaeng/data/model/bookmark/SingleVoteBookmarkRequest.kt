package com.example.sachosaeng.data.model.bookmark

import kotlinx.serialization.Serializable

@Serializable
data class SingleVoteBookmarkRequest(
    val voteId: Int
)
