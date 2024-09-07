package com.example.sachosaeng.data.model.category

import kotlinx.serialization.Serializable

@Serializable
data class AllCategoryIconResponse(
    val iconUrl: String,
    val backgroundColor: String? = null,
)