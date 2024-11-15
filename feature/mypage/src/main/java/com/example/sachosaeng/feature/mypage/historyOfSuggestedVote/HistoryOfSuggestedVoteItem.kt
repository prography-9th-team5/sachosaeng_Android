package com.example.sachosaeng.feature.mypage.historyOfSuggestedVote

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.getRegisterStatusImageWithColor
import com.sachosaeng.app.core.model.RegisterStatus
import com.sachosaeng.app.core.model.SuggestedVoteInfo
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_White

@Composable
fun HistoryOfSuggestedVoteItem(
    voteInfo: SuggestedVoteInfo,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Gs_White)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = voteInfo.title,
            fontWeight = FontWeight.W700,
            fontSize = 14.sp,
            color = Gs_Black
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            val (image, textColor, statusTextResId) = getRegisterStatusImageWithColor(voteInfo.registerStatus)
            Image(
                modifier = Modifier.padding(end = 4.dp),
                painter = painterResource(id = image),
                contentDescription = null
            )
            Text(
                text = stringResource(id = statusTextResId),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                color = textColor
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryOfSuggestedVoteItemPreview() {
    HistoryOfSuggestedVoteItem(SuggestedVoteInfo(1, "투표 제목", RegisterStatus.APPROVED))
}