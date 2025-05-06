package com.sachosaeng.app.data.repository.user

import com.sachosaeng.app.core.model.User
import com.sachosaeng.app.core.model.UserScore
import com.sachosaeng.app.data.model.user.UserInfoResponse

object UserMapper {
    fun UserInfoResponse.toDomain(): User {
        return User(
            id = userId.toLong(),
            name = nickname,
            email = "",
            userTypeName = userType,
            level = level,
            score = levelScore,
            voteScore = UserScore(
                count = voteParticipationCount,
                score = voteParticipationScore
            ),
            registerVoteScore = UserScore(
                count = voteRegistrationCount,
                score = voteRegistrationScore
            ),
            readArticleScore = UserScore(
                count = similarInformationViewCount,
                score = similarInformationViewScore
            ),
            maxScore = levelMaxScore
        )
    }
}