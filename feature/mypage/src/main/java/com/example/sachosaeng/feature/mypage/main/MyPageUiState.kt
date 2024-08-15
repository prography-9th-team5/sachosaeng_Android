package com.example.sachosaeng.feature.mypage.main

data class MyPageUiState (
    val levelText : String = "",
    val userName : String = "",
    val versionInfo : String = "1.0.0",
    val logoutDialogState: Boolean = false
)
