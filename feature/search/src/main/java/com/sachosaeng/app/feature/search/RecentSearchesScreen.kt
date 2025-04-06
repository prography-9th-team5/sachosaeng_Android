package com.sachosaeng.app.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.component.button.CloseButton
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.VoteInfo
import com.sachosaeng.app.core.model.VoteList
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.VoteCardByCategory
import com.sachosaeng.app.core.ui.theme.Gs_G5

@Composable
fun RecentSearchesScreen(
    modifier: Modifier = Modifier,
    state: SearchUiState,
    onDeleteRecentSearches: (String) -> Unit,
    navigateToVoteCard: (Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
    ) {
        item {
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(bottom = 11.dp),
                text = stringResource(id = R.string.recent_query)
            )
        }
        items(items = state.recentSearches) { item ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 13.dp)
            ) {
                item?.let {
                    Text(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.weight(1f),
                        text = item
                    )
                    CloseButton(
                        modifier = Modifier.size(16.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Gs_G5
                        ),
                        onCloseClick = {
                            onDeleteRecentSearches(item)
                        }
                    )
                }
            }
        }
        item {
            if (state.recommendVotes.voteInfo.isNotEmpty()) {
                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(bottom = 11.dp, top = 36.dp),
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

@Preview(showBackground = true)
@Composable
fun RecentSearchesScreenPreview() {
    RecentSearchesScreen(
        state = SearchUiState(
            recentSearches = listOf("Search 1", "Search 2", "Search 3"),
            recommendVotes = VoteList(
                voteInfo = listOf(
                    VoteInfo(
                        id = 1,
                        title = "Vote Title",
                        category = Category(),
                        voteCount = 100,
                        isClosed = true,
                        isVoted = false,
                    ),
                ),
                category = Category(),
                description = "Description",
            )
        ),
        navigateToVoteCard = { _, _ -> },
        onDeleteRecentSearches = {}
    )
}