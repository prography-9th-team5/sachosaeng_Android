package com.example.sachosaeng.data.model.vote

import kotlinx.serialization.Serializable

@Serializable
data class VoteOptionRequest(
    val chosenVoteOptionIds: List<Int?>
)