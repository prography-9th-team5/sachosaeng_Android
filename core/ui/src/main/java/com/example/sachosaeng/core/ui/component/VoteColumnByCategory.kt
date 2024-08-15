package com.example.sachosaeng.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.domain.model.VoteInfo

@Composable
fun VoteColumnByCategory(modifier: Modifier = Modifier, voteList: List<VoteInfo>) {
    Column(
        modifier = Modifier.padding(top = 14.dp, bottom = 36.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        voteList.forEachIndexed { index, vote ->
            VoteCard(
                modifier = modifier.padding(16.dp),
                vote.title,
                vote.imageUrl,
                vote.voteCount,
                index + 1
            )
        }
    }
}