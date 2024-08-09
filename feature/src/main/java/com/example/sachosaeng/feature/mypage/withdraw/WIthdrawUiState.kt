package com.example.sachosaeng.feature.mypage.withdraw

data class WIthdrawUiState(
    val userName: String = "",
    val reasonForWithdrawList: List<Int> = emptyList(),
    val reasonForWithdrawDetail: String = "",
    val reasonForWithdrawDetailFieldIsOpened: Boolean = false,
    val selectedReason: ReasonForWithdraw = ReasonForWithdraw.NONE,
    val snackBarMessage: String = ""
)

enum class ReasonForWithdraw {
    DISSATISFACTION,
    LACK_OF_CONTENT,
    NO_LONGER_USE,
    ETC,
    NONE
}