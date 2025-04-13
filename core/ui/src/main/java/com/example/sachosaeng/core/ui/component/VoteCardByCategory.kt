package com.sachosaeng.app.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sachosaeng.app.core.model.Category
import com.sachosaeng.app.core.model.VoteInfo

@Composable
fun VoteCardByCategory(
    modifier: Modifier = Modifier,
    voteList: List<VoteInfo>,
    rankinTextVisibility: Boolean = false,
    onVoteClick: (Int) -> Unit = {}
) {
    LazyRow (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
         items(count = voteList.size) { index ->
            VoteSmallCard(
                modifier = Modifier
                    .clickable {
                        onVoteClick(voteList[index].id)
                    },
                isVoted = voteList[index].isVoted,
                text = voteList[index].title,
                backgroundColorCode = voteList[index].category.color,
                iconUrl = voteList[index].category.imageUrl,
                voteCount = voteList[index].voteCount,
            )
        }
    }
}

@Composable
@Preview
fun VoteCardByCategoryPreview() {
    VoteCardByCategory(
        voteList = listOf(
            VoteInfo(
                id = 1,
                title = "titletitletitletitletitleti",
                category = Category(
                    id = 1,
                    name = "name",
                    color = "#000000",
                    imageUrl = "https://www.example.com"
                ),
                voteCount = 1,
                isVoted = false,
                isClosed = false
            )
        ),
        onVoteClick = {},
    )
}