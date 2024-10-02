package com.sachosaeng.app.feature.mypage.withdraw

import com.sachosaeng.app.core.ui.WithdrawReason

data class WIthdrawUiState(
    val userName: String = "",
    val reasonForWithdrawList: List<Int> = emptyList(),
    val reasonForWithdrawDetail: String = "",
    val reasonForWithdrawDetailFieldIsOpened: Boolean = false,
    val selectedReason: WithdrawReason? = null,
)