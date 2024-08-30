package com.example.sachosaeng.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String
)
