package com.example.sachosaeng.feature.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.model.VoteOption
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.feature.vote.component.DailyVoteDetailCard
import com.example.sachosaeng.feature.vote.component.VoteDetailCard

@Composable
fun DailyVoteDetailScreen(
    navigateToBackStack: () -> Unit,
    onSelectOption: (Int) -> Unit,
    onBookmarkVote: () -> Unit,
    onVoteComplete: () -> Unit,
    vote: Vote,
) {
    DailyVoteDetailScreen(
        vote = vote,
        onSelectOption = onSelectOption,
        onBookmarkVote = onBookmarkVote,
        onVoteComplete = onVoteComplete
    )
}


@Composable
internal fun DailyVoteDetailScreen(
    modifier: Modifier = Modifier,
    vote: Vote,
    onBookmarkVote: () -> Unit,
    onVoteComplete: () -> Unit,
    onSelectOption: (Int) -> Unit = { }
) {
    Column(
        modifier = modifier
            .background(Gs_G2)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            item {
                Column {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = stringResource(id = string.daily_vote),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.W700
                    )
                    DailyVoteDetailCard(
                        modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                        isBookmarked = vote.isBookmarked,
                        onBookmarkButtonClicked = onBookmarkVote,
                        selectedOptionIndex = vote.selectedOptionIds,
                        onSelectOption = onSelectOption,
                        vote = vote
                    )
                }
            }

            item {
                SachoSaengButton(
                    enabled = vote.selectedOptionIds.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    text = stringResource(id = if (vote.isVoted) string.more_vote_button_label else string.todays_vote_complete_label),
                    onClick = onVoteComplete
                )
            }
        }
    }
}

@Preview
@Composable
fun DailyVoteDetailScreenPreview() {
    DailyVoteDetailScreen(
        vote = Vote(
            title = "친한 사수분 결혼식 축의금 얼마가 좋을까요?",
            count = 1000,
            option = listOf(
                VoteOption(
                    voteOptionId = 1,
                    content = "option1",
                    count = 100
                ),
                VoteOption(
                    voteOptionId = 2,
                    content = "option2",
                    count = 200
                )
            ),
            category = Category(
                name = "카테고리",
                imageUrl = ""
            )
        ), onSelectOption = {}, navigateToBackStack = {}, onBookmarkVote = {}, onVoteComplete = {})
}