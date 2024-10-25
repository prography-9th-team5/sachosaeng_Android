package com.example.sachosaeng.feature.addvote

data class AddVoteUiState(
    val title: String = "",
    val description: String = "",
    val options: List<String> = emptyList()
)