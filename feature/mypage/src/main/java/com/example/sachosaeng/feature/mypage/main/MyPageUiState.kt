package com.sachosaeng.app.feature.mypage.main

import com.sachosaeng.app.core.ui.UserType

data class MyPageUiState (
    val levelText : String = "",
    val userName : String = "",
    val userType: UserType = UserType.OTHER,
    val versionInfo : String = "1.0.0",
    val logoutDialogState: Boolean = false
)
