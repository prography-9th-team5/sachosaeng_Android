package com.example.sachosaeng.data.model.vote

import com.example.sachosaeng.data.model.category.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class VoteListInfoResponse(
    val description: String,
    val category: CategoryResponse,
    val votes: List<VoteInfoResponse>
)
