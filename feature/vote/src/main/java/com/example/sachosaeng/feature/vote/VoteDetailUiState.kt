package com.example.sachosaeng.feature.vote

import com.example.sachosaeng.core.model.Vote

data class VoteDetailUiState(
    val vote: Vote = Vote(),
    val isCompleteState: Boolean = false,
    val completeIconImageRes: Int? = null
)