package com.sachosaeng.app.core.model

data class SuggestedVoteInfo(
    val id: Int,
    val title: String,
    val registerStatus: RegisterStatus,
)

enum class RegisterStatus {
    APPROVED,
    REJECTED,
    PENDING;

    companion object {
        fun fromName(name: String) = entries.find { it.name == name } ?: PENDING
    }
}