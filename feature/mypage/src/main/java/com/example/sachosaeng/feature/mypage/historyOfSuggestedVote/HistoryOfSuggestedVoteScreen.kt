package com.example.sachosaeng.feature.mypage.historyOfSuggestedVote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.sachosaeng.app.core.model.SuggestedVoteInfo
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.sachosaeng.app.feature.bookmark.component.EmptyScreen
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HistoryOfSuggestedVoteScreen(
    navigateToBackStack: () -> Unit,
    viewModel: HistoryOfSuggestedVoteViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()
    HistoryOfSuggestedVoteScreen(state = state.value, navigateToBackStack = navigateToBackStack, onVoteItemClick = viewModel::onVoteItemClick)
}

@Composable
internal fun HistoryOfSuggestedVoteScreen(
    modifier: Modifier = Modifier,
    navigateToBackStack: () -> Unit = {},
    onVoteItemClick: (SuggestedVoteInfo) -> Unit = {},
    state: HistoryOfSuggestedVoteUiState
) {
    val voteList = state.voteList.collectAsLazyPagingItems()

    if (voteList.itemCount == 0 && voteList.loadState.refresh.endOfPaginationReached) {
        EmptyScreen(
            modifier = modifier,
            emptyLabel = stringResource(id = R.string.suggested_vote_is_empty_description)
        )
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                SachosaengDetailTopAppBar(
                    modifier = Modifier.padding(vertical = 20.dp),
                    navigateToBackStack = navigateToBackStack,
                    title = stringResource(id = R.string.vote_history_title),
                    fontWeight = FontWeight.W700,
                    fontSize = 18
                )
            }
            items(voteList.itemCount) { index ->
                voteList[index]?.let { HistoryOfSuggestedVoteItem(voteInfo = it, onVoteItemClick = onVoteItemClick ) }
            }
        }
    }
}