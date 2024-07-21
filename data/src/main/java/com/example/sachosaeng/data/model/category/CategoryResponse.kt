package com.example.sachosaeng.data.model.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse (
    val categoryId: Int,
    val name: String,
    val iconUrl: String,
    val backgroundColor: String,
    val textColor: String
)