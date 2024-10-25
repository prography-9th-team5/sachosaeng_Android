package com.sachosaeng.app.data.model.vote

import com.sachosaeng.app.data.model.category.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class VoteListInfoResponse(
    val category: CategoryResponse,
    val votes: List<VoteInfoResponse>
)

@Serializable
data class VoteListInfoByCategoryResponse(
    val description: String,
    val category: CategoryResponse,
    val votes: List<VoteInfoResponse>
)
