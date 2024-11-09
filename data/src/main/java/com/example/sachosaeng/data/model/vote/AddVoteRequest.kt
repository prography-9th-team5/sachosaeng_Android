package com.example.sachosaeng.data.model.vote

import kotlinx.serialization.Serializable

@Serializable
data class AddVoteRequest(
    val title: String,
    val isMultipleChoiceAllowed: Boolean,
    val voteOptions: List<String>,
    val categoryIds: List<Int>,
)
