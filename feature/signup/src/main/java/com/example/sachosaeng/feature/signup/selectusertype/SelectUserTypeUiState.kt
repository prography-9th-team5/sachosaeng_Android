package com.sachosaeng.app.feature.signup.selectusertype

import com.sachosaeng.app.core.ui.UserType

data class SelectUserTypeUiState(
    val selectedType: UserType = UserType.STUDENT
)

