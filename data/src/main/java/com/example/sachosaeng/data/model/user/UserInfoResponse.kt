package com.example.sachosaeng.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse (
    val userId: Int,
    val nickname: String,
    val userType: String,
)