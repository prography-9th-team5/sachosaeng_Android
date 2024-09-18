package com.example.sachosaeng.core.model

import com.example.sachosaeng.core.domain.constant.OAuthType

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val oAuthType: OAuthType = OAuthType.KAKAO,
    val userType: String,
)