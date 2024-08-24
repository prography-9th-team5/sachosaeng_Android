package com.example.sachosaeng.core.ui

import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.R.string

enum class UserType(val userTypeImageRes: Int, val userTypeLabelRes: Int) {
    STUDENT(
        userTypeImageRes = drawable.ic_signup_complete_student,
        userTypeLabelRes = string.user_type_student,
    ),
    NEW_EMPLOYEE(
        userTypeImageRes = drawable.ic_signup_complete_newcomer,
        userTypeLabelRes = string.user_type_newcomer
    ),
    JOB_SEEKER(
        userTypeImageRes = drawable.ic_signup_jobseeker,
        userTypeLabelRes = string.user_type_jobseeker
    ),
    OTHER(
        userTypeImageRes = drawable.ic_signup_complete_etc,
        userTypeLabelRes = string.user_type_etc
    );

    companion object {
        fun getType(typeName: String): UserType? {
            return enumValues<UserType>().firstOrNull { it.name == typeName }
        }
    }
}