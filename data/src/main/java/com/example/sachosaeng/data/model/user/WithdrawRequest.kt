package com.example.sachosaeng.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class WithdrawRequest (
    val reason: String,
)