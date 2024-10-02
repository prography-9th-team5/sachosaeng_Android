package com.sachosaeng.app.feature.signup.signupcomplete

import com.sachosaeng.app.core.ui.UserType

data class SignUpCompleteUiState (
    val userType : UserType = UserType.NEW_EMPLOYEE,
    val userEmail : String = "",
    val isShow : Boolean = true
)