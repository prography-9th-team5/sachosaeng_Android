package com.example.sachosaeng.data.model.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    val categories: List<CategoryResponse>
)

@Serializable
data class CategoryResponse(
    val categoryId: Int? = null,
    val name: String,
    val iconUrl: String? = null,
    val backgroundColor: String? = null,
    val textColor: String? = null
)