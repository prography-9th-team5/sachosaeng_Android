package com.example.sachosaeng.core.model
data class Vote(
    val id: Int = 0,
    val title: String = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
    val count: Int = 1000,
    val category: Category = Category(),
    val option: List<VoteOption> = emptyList(),
    val selectedOptionIds: List<Int?> = emptyList(),
    var isBookmarked: Boolean = false,
    val isClosed: Boolean = false,
    val isVoted: Boolean = false,
    val isMultipleChoiceAllowed: Boolean = false,
    val description: String = "",
)

data class VoteOption(
    val voteOptionId: Int = 0,
    val content: String = "option1",
    val count: Int = 100,
)