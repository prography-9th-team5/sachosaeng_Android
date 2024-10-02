package com.sachosaeng.app.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class NicknameRequest (
    val nickname: String
)