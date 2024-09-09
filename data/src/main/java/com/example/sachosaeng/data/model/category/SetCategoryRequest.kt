package com.example.sachosaeng.data.model.category

import kotlinx.serialization.Serializable

@Serializable
data class SetCategoryRequest(
    val categoryIds: List<Int?>,
)