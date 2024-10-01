package com.sachosaeng.app.data.model.category

import kotlinx.serialization.Serializable

@Serializable
data class SetCategoryRequest(
    val categoryIds: List<Int?>,
)