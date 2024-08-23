package com.example.sachosaeng.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val code: Int,
    val message: String,
    val data: T
)
