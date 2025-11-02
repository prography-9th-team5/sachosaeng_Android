package com.example.sachosaeng.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserFcmTokenRequest (
    val device: String,
    val token: String,
    val platform: String
)