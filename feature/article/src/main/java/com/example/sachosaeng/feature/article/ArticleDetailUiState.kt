package com.example.sachosaeng.feature.article

import com.example.sachosaeng.core.model.Category

data class ArticleDetailUiState(
    val articleId: Int? = null,
    val title: String = "",
    val subtitle: String = "",
    val content: String = "",
    val category: Category = Category(),
    val isBookmarked: Boolean = false,
    val referenceName: String = ""
)
