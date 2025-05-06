package com.sachosaeng.app.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse (
    val userId: Int,
    val nickname: String,
    val userType: String,
    val voteParticipationScore: Int,
    val voteParticipationCount: Int,
    val similarInformationViewScore: Int,
    val similarInformationViewCount: Int,
    val voteRegistrationScore: Int,
    val voteRegistrationCount: Int,
    val level: Int,
    val levelScore: Int,
    val levelMaxScore: Int
)