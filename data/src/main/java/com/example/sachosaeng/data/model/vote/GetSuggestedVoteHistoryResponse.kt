package com.example.sachosaeng.data.model.vote

import kotlinx.serialization.Serializable

@Serializable
data class GetSuggestedVoteHistoryResponse(
    val votes: List<SuggestedVote>,
    val hasNext: Boolean,
    val nextCursor: Int?
)

@Serializable
data class SuggestedVote(
    val voteId: Int,
    val title: String,
    val status: String
)