package com.example.sachosaeng.feature.signup.selectusertype

data class SelectUserTypeUiState (
    val selectedType : UserType = UserType.STUDENT
)

enum class UserType {
    STUDENT,
    NEWCOMER,
    JOBSEEKER,
    ETC
}