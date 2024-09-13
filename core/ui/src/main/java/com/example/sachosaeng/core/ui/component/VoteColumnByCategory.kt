package com.example.sachosaeng.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.model.VoteInfo

@Composable
fun VoteColumnByCategory(
    modifier: Modifier = Modifier,
    voteList: List<VoteInfo>,
    rankinTextVisibility: Boolean = false,
    onVoteClick: (Int) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        voteList.forEachIndexed { index, vote ->
            VoteCard(
                modifier = modifier
                    .padding(16.dp)
                    .clickable {
                        onVoteClick(vote.id)
                    },
                text = vote.title,
                backgroundColorCode = vote.category.color,
                iconUrl = vote.category.imageUrl,
                voteCount = vote.voteCount,
                ranking = index + 1,
                rankingTextVisibility = rankinTextVisibility
            )
        }
    }
}
