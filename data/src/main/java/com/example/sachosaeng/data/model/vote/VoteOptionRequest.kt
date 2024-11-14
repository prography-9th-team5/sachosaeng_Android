package com.sachosaeng.app.data.model.vote

import kotlinx.serialization.Serializable

@Serializable
data class VoteOptionRequest(
    val chosenVoteOptionIds: List<Int?>
)