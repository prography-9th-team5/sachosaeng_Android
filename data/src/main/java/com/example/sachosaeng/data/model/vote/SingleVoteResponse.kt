package com.sachosaeng.app.data.model.vote

import kotlinx.serialization.Serializable

@Serializable
data class SingleCategoryVoteResponse(
    val votes: List<VoteInfoResponse>
)


