package com.sachosaeng.app.data.model.vote

import com.sachosaeng.app.data.model.category.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class VoteInfoResponse(
    val voteId: Int,
    val title: String,
    val participantCount: Int? = null,
    val isVoted: Boolean,
    val category: CategoryResponse? = null,
    val isClosed: Boolean,
)
