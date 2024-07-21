package com.example.sachosaeng.feature.signup.signupcomplete

import com.example.sachosaeng.feature.signup.selectusertype.UserType

data class SignUpCompleteUiState (
    val userType : UserType = UserType.NEWCOMER,
    val userName : String = "",
    val isShow : Boolean = true
)