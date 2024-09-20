package com.example.sachosaeng.data.model.article

import kotlinx.serialization.Serializable

@Serializable
data class SimilarArticleResponse (
    val information: List<SimilarArticleInfo>,
)

@Serializable
data class SimilarArticleInfo (
    val informationId: Int,
    val title: String
)