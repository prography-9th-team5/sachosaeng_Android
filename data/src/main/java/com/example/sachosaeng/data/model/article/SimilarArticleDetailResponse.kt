package com.example.sachosaeng.data.model.article

import com.example.sachosaeng.data.model.category.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class SimilarArticleDetailResponse(
    val informationId: Int,
    val isBookmarked: Boolean,
    val title: String,
    val subtitle: String?,
    val content: String,
    val category: CategoryResponse,
    val referenceName: String
)
