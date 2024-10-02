package com.sachosaeng.app.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class JoinRequest(
    val email: String,
    val userType: String,
)
