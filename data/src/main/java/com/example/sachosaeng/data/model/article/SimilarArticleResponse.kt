package com.example.sachosaeng.data.model.article

import kotlinx.serialization.Serializable

@Serializable
data class SimilarArticleResponse (
    val informationId: Int,
    val title: String
)