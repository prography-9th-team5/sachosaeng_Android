package com.sachosaeng.app.core.ui

import com.sachosaeng.app.core.ui.R.drawable
import com.sachosaeng.app.core.ui.R.string

enum class UserType(
    val userTypeImageRes: Int,
    val userTypeIconImageRes: Int,
    val userTypeLabelRes: Int,
    val userTypeLargeLv1ImageRes: Int,
    val userTypeLargeLv2ImageRes: Int
) {
    STUDENT(
        userTypeIconImageRes = drawable.ic_profile_student,
        userTypeImageRes = drawable.ic_signup_complete_student,
        userTypeLargeLv1ImageRes = drawable.image_student_lv1,
        userTypeLargeLv2ImageRes = drawable.image_student_lv2,
        userTypeLabelRes = string.user_type_student,
    ),
    NEW_EMPLOYEE(
        userTypeIconImageRes = drawable.ic_profile_newcomer,
        userTypeImageRes = drawable.ic_signup_complete_newcomer,
        userTypeLargeLv1ImageRes = drawable.image_newcomer_lv1,
        userTypeLargeLv2ImageRes = drawable.image_newcomer_lv2,
        userTypeLabelRes = string.user_type_newcomer
    ),
    JOB_SEEKER(
        userTypeIconImageRes = drawable.ic_profile_jobseeker,
        userTypeImageRes = drawable.ic_signup_jobseeker,
        userTypeLargeLv1ImageRes = drawable.image_jobseeker_lv1,
        userTypeLargeLv2ImageRes = drawable.image_jobseeker_lv2,
        userTypeLabelRes = string.user_type_jobseeker
    ),
    OTHER(
        userTypeIconImageRes = drawable.ic_profile_etc,
        userTypeImageRes = drawable.ic_signup_complete_etc,
        userTypeLargeLv1ImageRes = drawable.image_other_lv1,
        userTypeLargeLv2ImageRes = drawable.image_other_lv2,
        userTypeLabelRes = string.user_type_etc
    );

    fun getLargeImageRes(level: Int): Int {
        return when (level) {
            1 -> userTypeLargeLv1ImageRes
            2 -> userTypeLargeLv2ImageRes
            else -> drawable.image_other_lv1 // 기본 fallback
        }
    }

    companion object {
        fun getType(typeName: String): UserType? {
            return enumValues<UserType>().firstOrNull { it.name == typeName }
        }
    }
}