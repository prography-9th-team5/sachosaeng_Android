package com.sachosaeng.app.core.model

data class Category(
    val id: Int = -1,
    var color: String = "#D0D5DD",
    val name: String = "전체",
    val textColor: String = "#000000",
    val imageUrl: String? = null
)
