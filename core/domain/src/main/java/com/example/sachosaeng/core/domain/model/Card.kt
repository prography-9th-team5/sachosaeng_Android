package com.example.sachosaeng.core.domain.model
data class Card(
    val id: Int,
    val title: String,
    val category: Category,
    val content: String,
    val isBookmarked: Boolean,
)