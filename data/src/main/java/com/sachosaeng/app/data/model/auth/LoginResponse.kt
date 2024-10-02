package com.sachosaeng.app.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String
)
