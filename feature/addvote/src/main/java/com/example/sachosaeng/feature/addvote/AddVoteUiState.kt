package com.example.sachosaeng.feature.addvote

import com.sachosaeng.app.core.model.Category

data class AddVoteUiState(
    val title: String = "",
    val description: String = "",
    val canMultipleCheck: Boolean = false,
    val options: List<String> = listOf("","","",""),
    val category: List<Category> = emptyList(),
    val selectedCategory: List<Category> = emptyList()
)