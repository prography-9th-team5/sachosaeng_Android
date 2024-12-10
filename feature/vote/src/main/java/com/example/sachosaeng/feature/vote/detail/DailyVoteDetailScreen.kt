package com.sachosaeng.app.feature.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.util.FirebaseUtil
import com.example.sachosaeng.core.util.FirebaseUtil.SCREEN_NAME_DAILY_VOTE
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.Vote
import com.sachosaeng.app.core.model.VoteOption
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.core.ui.component.button.SachoSaengButton
import com.sachosaeng.app.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.feature.vote.component.DailyVoteDetailCard

@Composable
fun DailyVoteDetailScreen(
    vote: Vote,
    modifier: Modifier = Modifier,
    onBookmarkVote: () -> Unit,
    onVoteComplete: () -> Unit,
    onSelectOption: (Int) -> Unit = { },
    navigateToBackStack: () -> Unit = { },
) {
    LaunchedEffect(Unit) {
        FirebaseUtil.setScreenView(SCREEN_NAME_DAILY_VOTE)
    }
    val isVoted by remember {
        mutableStateOf(vote.isVoted)
    }
    Column(
        modifier = modifier
            .background(Gs_G2)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            item {
                DailyVoteTopBar(isVoted, navigateToBackStack)
            }
            item {
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
        Spacer(modifier = Modifier.weight(1f))
        SachoSaengButton(
            enabled = vote.selectedOptionIds.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            text = stringResource(id = if (vote.isVoted) string.more_vote_button_label else string.todays_vote_complete_label),
            onClick = if (vote.isVoted) navigateToBackStack else onVoteComplete
        )
    }
}

@Composable
fun DailyVoteTopBar(isVoted: Boolean, navigateToBackStack: () -> Unit) {
    when (!isVoted) {
        true -> {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                text = stringResource(id = string.daily_vote),
                fontSize = 26.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Start
            )
        }

        false -> {
            SachosaengDetailTopAppBar(
                modifier = Modifier.padding(20.dp),
                navigateToBackStack = navigateToBackStack,
                title = stringResource(id = string.daily_vote),
                fontWeight = FontWeight.W700,
                fontSize = 18
            )
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
        ),
        onSelectOption = {},
        navigateToBackStack = {},
        onBookmarkVote = {},
        onVoteComplete = {},
    )
}