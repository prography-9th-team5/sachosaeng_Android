package com.example.sachosaeng.core.domain.model
data class Vote(
    val id: Int = 0,
    val title: String = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
    val count : Int = 1000,
    val category: Category = Category(),
    val option: List<String> = listOf("option1", "option2"),
    val selectedOption: String = "",
    val isBookmarked: Boolean = false,
)