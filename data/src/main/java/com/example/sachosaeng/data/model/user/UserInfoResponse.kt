package com.sachosaeng.app.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse (
    val userId: Int,
    val nickname: String,
    val userType: String,
)