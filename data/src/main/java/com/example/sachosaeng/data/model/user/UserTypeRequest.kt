package com.sachosaeng.app.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserTypeRequest (
    val userType: String
)