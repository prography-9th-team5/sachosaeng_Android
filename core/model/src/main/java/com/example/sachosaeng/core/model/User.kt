package com.sachosaeng.app.core.model

import com.sachosaeng.app.core.domain.constant.OAuthType

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val oAuthType: OAuthType = OAuthType.KAKAO,
    val userType: String,
)