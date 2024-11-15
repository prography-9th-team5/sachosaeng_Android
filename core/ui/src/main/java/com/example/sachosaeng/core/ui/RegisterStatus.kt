package com.example.sachosaeng.core.ui

import androidx.compose.ui.graphics.Color
import com.sachosaeng.app.core.model.RegisterStatus
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.theme.BLUE_0BA5EC
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.RED_F04438

fun getRegisterStatusImageWithColor(registerStatus: RegisterStatus): RegisterStatusInfo {
    return when (registerStatus) {
        RegisterStatus.APPROVED -> RegisterStatusInfo(
            R.drawable.ic_blue_outlined_circle,
            BLUE_0BA5EC,
            R.string.vote_status_approved
        )

        RegisterStatus.REJECTED -> RegisterStatusInfo(
            R.drawable.ic_red_failed,
            RED_F04438,
            R.string.vote_status_failed
        )

        RegisterStatus.PENDING -> RegisterStatusInfo(
            R.drawable.ic_black_pending,
            Gs_G6,
            R.string.vote_status_pending
        )
    }
}

data class RegisterStatusInfo(
    val image: Int,
    val textColor: Color,
    val statusTextResId: Int
)
