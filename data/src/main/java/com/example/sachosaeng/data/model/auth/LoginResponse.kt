package com.example.sachosaeng.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)
