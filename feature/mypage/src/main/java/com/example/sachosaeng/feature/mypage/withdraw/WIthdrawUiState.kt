package com.example.sachosaeng.feature.mypage.withdraw

import com.example.sachosaeng.core.ui.WithdrawReason

data class WIthdrawUiState(
    val userName: String = "",
    val reasonForWithdrawList: List<Int> = emptyList(),
    val reasonForWithdrawDetail: String = "",
    val reasonForWithdrawDetailFieldIsOpened: Boolean = false,
    val selectedReason: WithdrawReason? = null,
)