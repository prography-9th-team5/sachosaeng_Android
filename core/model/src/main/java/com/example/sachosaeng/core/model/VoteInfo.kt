package com.example.sachosaeng.core.model

data class VoteInfo(
    val id: Int,
    val title: String,
    val category: Category,
    val voteCount: Int? = null,
    val isClosed: Boolean,
    val isVoted: Boolean
)