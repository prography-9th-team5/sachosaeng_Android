package com.example.sachosaeng.feature.signup.selectusertype

import com.example.sachosaeng.feature.R.drawable
import com.example.sachosaeng.core.ui.R.string

data class SelectUserTypeUiState(
    val selectedType: UserType = UserType.STUDENT
)

enum class UserType(val userTypeImageRes: Int, val userTypeLabelRes: Int) {
    STUDENT(
        userTypeImageRes = drawable.ic_signup_complete_student,
        userTypeLabelRes = string.user_type_student
    ),
    NEWCOMER(
        userTypeImageRes = drawable.ic_signup_complete_newcomer,
        userTypeLabelRes = string.user_type_newcomer
    ),
    JOBSEEKER(
        userTypeImageRes = drawable.ic_signup_jobseeker,
        userTypeLabelRes = string.user_type_jobseeker
    ),
    ETC(
        userTypeImageRes = drawable.ic_signup_complete_etc,
        userTypeLabelRes = string.user_type_etc
    );
}