package com.example.sachosaeng.core.model

data class SimilarArticleDetail (
    val articleId: Int,
    val title: String,
    val subtitle: String,
    val content: String,
    val category: Category,
    val isBookmarked: Boolean,
    val referenceName: String
)