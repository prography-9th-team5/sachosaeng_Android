package com.example.sachosaeng.feature.signup.signupcomplete

import com.example.sachosaeng.core.ui.UserType

data class SignUpCompleteUiState (
    val userType : UserType = UserType.NEW_EMPLOYEE,
    val userName : String = "",
    val isShow : Boolean = true
)