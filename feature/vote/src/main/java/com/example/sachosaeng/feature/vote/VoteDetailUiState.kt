package com.sachosaeng.app.feature.vote

import com.sachosaeng.app.core.model.SimilarArticle
import com.sachosaeng.app.core.model.Vote

data class VoteDetailUiState(
    val vote: Vote = Vote(),
    val isShown: Boolean = false,
    val isCompleteState: Boolean = false,
    val completeIconImageRes: Int? = null,
    val similarArticle: List<SimilarArticle> = emptyList(),
    val isDailyVote: Boolean = false
)