package com.example.sachosaeng.feature.signup.selectusertype

import com.example.sachosaeng.feature.R

data class SelectUserTypeUiState(
    val selectedType: UserType = UserType.STUDENT
)

enum class UserType(val userTypeImageRes: Int, val userTypeLabelRes: Int) {
    STUDENT(
        userTypeImageRes = R.drawable.ic_signup_complete_student,
        userTypeLabelRes = R.string.user_type_student
    ),
    NEWCOMER(
        userTypeImageRes = R.drawable.ic_signup_complete_newcomer,
        userTypeLabelRes = R.string.user_type_newcomer
    ),
    JOBSEEKER(
        userTypeImageRes = R.drawable.ic_signup_jobseeker,
        userTypeLabelRes = R.string.user_type_jobseeker
    ),
    ETC(
        userTypeImageRes = R.drawable.ic_signup_complete_etc,
        userTypeLabelRes = R.string.user_type_etc
    );
}