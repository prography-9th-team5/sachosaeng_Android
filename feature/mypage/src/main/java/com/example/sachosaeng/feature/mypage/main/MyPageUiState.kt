package com.sachosaeng.app.feature.mypage.main

import com.sachosaeng.app.core.model.User
import com.sachosaeng.app.core.model.UserScore

data class MyPageUiState(
    val userInfo: User = User(
        name = "",
        userTypeName = "",
        level = 1,
        voteScore = UserScore(),
        registerVoteScore = UserScore(),
        readArticleScore = UserScore(),
        score = 0
    ),
    val versionInfo: String = "1.0.0",
    val logoutDialogState: Boolean = false,
    val downloadCompleteDialogState: Boolean = false,
)

sealed class MyPageSideEffect {
    data object NavigateToAlertPage : MyPageSideEffect()
}