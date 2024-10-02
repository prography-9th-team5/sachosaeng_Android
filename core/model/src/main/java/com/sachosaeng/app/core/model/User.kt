package com.sachosaeng.app.core.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val oAuthType: OAuthType = OAuthType.KAKAO,
    val userType: String,
)