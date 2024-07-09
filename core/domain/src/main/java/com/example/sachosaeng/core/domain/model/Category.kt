package com.example.sachosaeng.core.domain.model


data class Category(
    val id: Int = 0,
    val color: String = "FF000000",
    val name: String = "카테고리",
    val imageUrl: String? = null,
)
