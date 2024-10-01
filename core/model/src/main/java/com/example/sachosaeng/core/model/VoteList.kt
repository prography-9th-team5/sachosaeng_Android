package com.example.sachosaeng.core.model
data class VoteList(
    val category: Category,
    val description: String,
    val voteInfo: List<VoteInfo>
)
