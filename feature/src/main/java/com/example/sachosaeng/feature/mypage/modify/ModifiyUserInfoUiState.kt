package com.example.sachosaeng.feature.mypage.modify

import com.example.sachosaeng.feature.signup.selectusertype.UserType

data class ModifiyUserInfoUiState (
    val userName: String,
    val userType: UserType,
    val canSave: Boolean
)