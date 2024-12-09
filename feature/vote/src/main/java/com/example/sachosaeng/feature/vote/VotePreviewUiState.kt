package com.example.sachosaeng.feature.vote

import com.sachosaeng.app.core.model.Category

data class VotePreviewUiState(
    val title: String = "",
    val description: String = "",
    val canMultipleChoice: Boolean = false,
    val options: List<String> = listOf("", "", "", ""),
    val category: Category = Category()
)