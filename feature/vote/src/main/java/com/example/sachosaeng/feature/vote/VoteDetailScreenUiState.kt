package com.example.sachosaeng.feature.vote

import com.example.sachosaeng.core.model.Category

data class VoteDetailScreenUiState(
    val category: Category = Category(),
    val title: String = "",
    val count: Int = 0,
    val options: List<String> = emptyList(),
    val isBookmarked: Boolean = false
)