package com.sachosaeng.app.core.model

import com.sachosaeng.app.core.domain.constant.OAuthType

data class User(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val email: String = "",
    val oAuthType: OAuthType = OAuthType.KAKAO,
    val userTypeName: String,
    val level: Int = 1,
    val voteScore: UserScore = UserScore(),
    val registerVoteScore: UserScore = UserScore(),
    val readArticleScore: UserScore = UserScore(),
    val maxScore: Int = 0,
    val score: Int = 0,
    val userGrowthSystemConfirmed: Boolean = false,
    val levelUpNotification: Boolean = false,
)

data class UserScore(
    val count: Int = 0,
    val score: Int = 0
)