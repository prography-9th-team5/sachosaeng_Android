package com.example.sachosaeng.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserFcmTokenRequest (
    val device: String,
    val token: String,
    val platform: String
)

@Serializable
data class PushMessageTestRequest (
    val title: String,
    val body: String
)

@Serializable
data class PushMessageTestByTokenRequest (
    val token: String,
    val title: String,
    val body: String
)