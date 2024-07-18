package com.example.sachosaeng.feature.signup

data class SelectUserTypeUiState (
    val selectedType : UserType = UserType.STUDENT
)


enum class UserType {
    STUDENT,
    NEWCOMER,
    JOBSEEKER,
    ETC
}