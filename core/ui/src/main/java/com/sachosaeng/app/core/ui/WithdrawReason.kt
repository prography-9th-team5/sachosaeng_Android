package com.sachosaeng.app.core.ui

import com.sachosaeng.app.core.ui.R.string

enum class WithdrawReason(
    val userTypeLabelRes: Int
) {
    DISSATISFACTION(
        userTypeLabelRes = string.reason_for_withdraw_1,
    ),
    LACK_OF_CONTENT(
        userTypeLabelRes = string.reason_for_withdraw_2
    ),
    NO_LONGER_USE(
        userTypeLabelRes = string.reason_for_withdraw_3
    ),
    ETC(
        userTypeLabelRes = string.reason_for_withdraw_4
    );

    companion object {
        fun getType(typeName: String): WithdrawReason? {
            return enumValues<WithdrawReason>().firstOrNull { it.name == typeName }
        }
    }
}