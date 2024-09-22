package com.example.sachosaeng.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class JoinResponse(
    val userId: Int,
    val loginToken: String,
)