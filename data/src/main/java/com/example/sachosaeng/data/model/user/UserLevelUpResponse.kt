package com.example.sachosaeng.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLevelUpResponse(
    val previousLevel: Int,
    val currentLevel: Int,
    val isLevelUp: Boolean,
    val isCompletedMaxLevel: Boolean,
)
