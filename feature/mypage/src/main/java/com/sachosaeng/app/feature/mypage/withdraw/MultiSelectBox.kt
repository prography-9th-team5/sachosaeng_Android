package com.sachosaeng.app.feature.mypage.withdraw

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.WithdrawReason
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G4
import com.sachosaeng.app.core.ui.theme.Gs_White

@Composable
fun MultiSelectBox(
    modifier: Modifier = Modifier,
    reason: WithdrawReason,
    selectedReason: WithdrawReason?,
    onSelect: (WithdrawReason) -> Unit = {}
) {
    val isSelected = selectedReason == reason
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp,
                color = if (isSelected) Gs_Black else Gs_White,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(color = Gs_White)
            .padding(vertical = 22.dp, horizontal = 16.dp)
            .noRippleClickable {
                onSelect(reason)
            }
    ) {
        Image(
            modifier = modifier.padding(end = 8.dp),
            painter = painterResource(id = R.drawable.ic_unchecked),
            contentDescription = null,
            colorFilter = ColorFilter.tint(if (isSelected) Gs_Black else Gs_G4)
        )
        Text(
            text = stringResource(id = reason.userTypeLabelRes),
            fontWeight = FontWeight.W500,
            fontSize = 18.sp
        )
    }
}