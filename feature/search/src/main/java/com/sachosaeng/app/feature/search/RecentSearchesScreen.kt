package com.sachosaeng.app.feature.search

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.VoteCardByCategory

@Composable
fun RecentSearchesScreen(
    modifier: Modifier = Modifier,
    state: SearchUiState,
    navigateToVoteCard: (Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(top = 60.dp, start = 20.dp, end = 20.dp),
    ) {
        item {
            if (state.recommendVotes.voteInfo.isNotEmpty()) {
                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(bottom = 11.dp),
                    text = stringResource(id = R.string.recommended_vote)
                )
                VoteCardByCategory(
                    rankinTextVisibility = true,
                    voteList = state.recommendVotes.voteInfo,
                    onVoteClick = { navigateToVoteCard(it, false) }
                )
            }
        }
    }
}