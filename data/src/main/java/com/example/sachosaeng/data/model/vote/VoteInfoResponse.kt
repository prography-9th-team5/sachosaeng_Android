package com.example.sachosaeng.data.model.vote

import com.example.sachosaeng.data.model.category.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class VoteInfoResponse(
    val voteId: Int,
    val title: String,
    val participantCount: Int? = null,
    val isVoted: Boolean,
    val category: CategoryResponse,
    val isClosed: Boolean,
)
