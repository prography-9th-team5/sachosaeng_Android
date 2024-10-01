package com.sachosaeng.app.data.model.vote

import com.sachosaeng.app.data.model.category.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class VoteDetailInfoResponse(
    val voteId: Int,
    val title: String,
    val participantCount: Int? = null,
    val isVoted: Boolean,
    val category: CategoryResponse,
    val isClosed: Boolean,
    val isMultipleChoiceAllowed: Boolean,
    val voteOptions: List<VoteOptionResponse>,
    val chosenVoteOptionId: List<Int?> = emptyList(),
    val description: String,
    val isBookmarked: Boolean,
)

@Serializable
data class VoteOptionResponse(
    val voteOptionId: Int,
    val content: String,
    val count: Int,
)