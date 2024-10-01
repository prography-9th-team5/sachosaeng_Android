package com.sachosaeng.app.feature.mypage.modifyUserInfo

import com.sachosaeng.app.core.ui.UserType

data class ModifiyUserInfoUiState (
    val userName: String,
    val userType: UserType,
    val canSave: Boolean
)