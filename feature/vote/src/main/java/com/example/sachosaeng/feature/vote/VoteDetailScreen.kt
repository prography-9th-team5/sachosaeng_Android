package com.sachosaeng.app.feature.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.SimilarArticle
import com.sachosaeng.app.core.model.Vote
import com.sachosaeng.app.core.model.VoteOption
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.core.ui.component.button.SachoSaengButton
import com.sachosaeng.app.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.feature.vote.component.VoteCompleteFooter
import com.sachosaeng.app.feature.vote.component.VoteCompleteScreen
import com.sachosaeng.app.feature.vote.component.VoteDetailCard
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun VoteScreen(
    navigateToBackStack: () -> Unit,
    navigateToArticleDetail: (Int, Int) -> Unit,
    viewModel: VoteDetailViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()

    when (state.value.isCompleteState) {
        true -> VoteCompleteScreen()
        false -> {
            when (state.value.isDailyVote) {
                true -> DailyVoteDetailScreen(
                    onSelectOption = viewModel::onSelectOption,
                    onBookmarkVote = viewModel::bookmarkButtonClick,
                    onVoteComplete = viewModel::vote,
                    navigateToBackStack = navigateToBackStack,
                    vote = state.value.vote
                )

                false -> VoteScreen(
                    vote = state.value.vote,
                    similarArticle = state.value.similarArticle,
                    completeDescriptionIconRes = state.value.completeIconImageRes,
                    navigateToBackStack = navigateToBackStack,
                    onBookmarkVote = viewModel::bookmarkButtonClick,
                    onVoteComplete = viewModel::vote,
                    onSelectOption = viewModel::onSelectOption,
                    navigateToArticleDetail = navigateToArticleDetail
                )
            }
        }
    }
}

@Composable
internal fun VoteScreen(
    modifier: Modifier = Modifier,
    vote: Vote,
    similarArticle: List<SimilarArticle> = emptyList(),
    completeDescriptionIconRes: Int? = null,
    onSelectOption: (Int) -> Unit = { },
    onBookmarkVote: () -> Unit = { },
    onVoteComplete: () -> Unit = { },
    navigateToBackStack: () -> Unit,
    navigateToArticleDetail: (Int, Int) -> Unit
) {
    Column(
        modifier = modifier
            .background(Gs_G2)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        SachosaengDetailTopAppBar(
            title = vote.category.name,
            navigateToBackStack = navigateToBackStack
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            item {
                VoteDetailCard(
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    isBookmarked = vote.isBookmarked,
                    onBookmarkButtonClicked = { onBookmarkVote() },
                    selectedOptionIndex = vote.selectedOptionIds,
                    onSelectOption = onSelectOption,
                    vote = vote
                )
            }
            item {
                if (vote.isVoted) VoteCompleteFooter(
                    modifier = modifier.padding(horizontal = 20.dp),
                    completeDescription = vote.description,
                    completeDescriptionIconRes = completeDescriptionIconRes,
                    similarArticleList = similarArticle,
                    navigateToArticleDetail = { id -> navigateToArticleDetail(id, vote.category.id) }
                )
            }
            item {
                SachoSaengButton(
                    enabled = vote.selectedOptionIds.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    text = stringResource(id = if (vote.isVoted) string.more_vote_button_label else string.confirm_label),
                    onClick = { if (vote.isVoted) navigateToBackStack() else onVoteComplete() }
                )
            }
        }
    }
}

@Preview
@Composable
fun VoteScreenPreview() {
    VoteScreen(
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
        navigateToBackStack = {},
        navigateToArticleDetail = { _, _ -> },
        onBookmarkVote = {},
        onSelectOption = {},
        onVoteComplete = {}
    )
}