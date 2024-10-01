package com.sachosaeng.app.core.ui

import com.sachosaeng.app.core.ui.R.drawable
import com.sachosaeng.app.core.ui.R.string

enum class UserType(
    val userTypeImageRes: Int,
    val userTypeIconImageRes: Int,
    val userTypeLabelRes: Int
) {
    STUDENT(
        userTypeIconImageRes = drawable.ic_profile_student,
        userTypeImageRes = drawable.ic_signup_complete_student,
        userTypeLabelRes = string.user_type_student,
    ),
    NEW_EMPLOYEE(
        userTypeIconImageRes = drawable.ic_profile_newcomer,
        userTypeImageRes = drawable.ic_signup_complete_newcomer,
        userTypeLabelRes = string.user_type_newcomer
    ),
    JOB_SEEKER(
        userTypeIconImageRes = drawable.ic_profile_jobseeker,
        userTypeImageRes = drawable.ic_signup_jobseeker,
        userTypeLabelRes = string.user_type_jobseeker
    ),
    OTHER(
        userTypeIconImageRes = drawable.ic_profile_etc,
        userTypeImageRes = drawable.ic_signup_complete_etc,
        userTypeLabelRes = string.user_type_etc
    );

    companion object {
        fun getType(typeName: String): UserType? {
            return enumValues<UserType>().firstOrNull { it.name == typeName }
        }
    }
}