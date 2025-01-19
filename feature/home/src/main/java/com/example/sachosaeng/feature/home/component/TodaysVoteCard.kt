package com.sachosaeng.app.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.component.card.HomeDailyVoteOptionRow
import com.example.sachosaeng.core.ui.component.card.OptionRow
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.Vote
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G3
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.util.extension.StringExtension.toColorResource

@Composable
fun TodaysVoteCard(vote: Vote, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(Gs_Black)
            .noRippleClickable { onClick() }
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Gs_G6)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.daily_vote),
                    fontSize = 12.sp, fontWeight = FontWeight.W500, color = Gs_G3
                )
            }
            Text(
                modifier = Modifier.padding(top = 12.dp, bottom = 16.dp),
                text = vote.title,
                color = Gs_White,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700
            )
            vote.option.forEach { option ->
                HomeDailyVoteOptionRow(
                    modifier = modifier.padding(top = 4.dp),
                    optionPercentage = (option.count * 100f) / vote.count,
                    percentageColorRes = vote.category.color,
                    percentageTextColorRes = Color(vote.category.textColor.toColorResource()),
                    isSeleceted = vote.selectedOptionIds.contains(option.voteOptionId),
                    text = option.content,
                )
            }
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = stringResource(id = R.string.vote_completed, vote.count),
                color = Gs_G5,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Preview
@Composable
fun TodaysVoteCardPreview() {
    TodaysVoteCard(
        vote = Vote(
            title = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
            category = Category(
                id = 1,
                name = "결혼식"
            ),
            isClosed = false,
            isVoted = false
        )
    )
}