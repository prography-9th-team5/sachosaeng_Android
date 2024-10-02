package com.sachosaeng.app.data.model.vote

import com.sachosaeng.app.data.model.category.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class VoteListInfoResponse(
    val description: String,
    val category: CategoryResponse,
    val votes: List<VoteInfoResponse>
)
