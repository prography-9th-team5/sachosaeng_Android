package com.example.sachosaeng.feature.vote

import com.example.sachosaeng.core.model.SimilarArticle
import com.example.sachosaeng.core.model.Vote

data class VoteDetailUiState(
    val vote: Vote = Vote(),
    val isShown: Boolean = false,
    val isCompleteState: Boolean = false,
    val completeIconImageRes: Int? = null,
    val similarArticle: List<SimilarArticle> = emptyList(),
    val isDailyVote: Boolean = false
)