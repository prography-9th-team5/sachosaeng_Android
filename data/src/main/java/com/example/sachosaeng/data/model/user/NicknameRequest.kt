package com.example.sachosaeng.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class NicknameRequest (
    val nickname: String
)