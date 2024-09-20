package com.example.sachosaeng.feature.vote.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.core.model.Vote
import com.example.sachosaeng.core.model.VoteOption
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.util.extension.IntExtension.toNumberOfPeople
import com.example.sachosaeng.core.util.extension.StringExtension.toColorResource

@Composable
fun DailyVoteDetailCard(
    modifier: Modifier = Modifier,
    vote: Vote,
    selectedOptionIndex: List<Int?>,
    isBookmarked: Boolean = false,
    onBookmarkButtonClicked: () -> Unit = { },
    onSelectOption: (Int) -> Unit = { }
) {
    Card(modifier = modifier, colors = CardDefaults.cardColors().copy(containerColor = Gs_White)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .background(color = Color(vote.category.color.toColorResource()))
        ) {
            CategoryIcon(imageUrl = vote.category.imageUrl)
            BookmarkButton(
                isBookmarked = isBookmarked,
                onBookmarkButtonClicked = onBookmarkButtonClicked
            )
        }
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = vote.title, fontWeight = FontWeight.W700, fontSize = 18.sp)
            Text(text = vote.count.toNumberOfPeople(), modifier = Modifier.padding(top = 16.dp))
            Column(
                modifier = Modifier.padding(top = 28.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                vote.option.forEach { option ->
                    DailyVoteOptionRow(
                        isVoted = vote.isVoted,
                        isSeleceted = selectedOptionIndex.contains(option.voteOptionId),
                        text = option.content,
                        onSelected = {
                            onSelectOption(option.voteOptionId)
                        }
                    )
                }
            }
            if (vote.isVoted) {
                Text(
                    text = stringResource(id = R.string.vote_complete_description),
                    color = Gs_G5,
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun CategoryIcon(imageUrl: String?) {
    AsyncImage(
        modifier = Modifier
            .size(68.dp)
            .padding(18.dp),
        model = imageUrl ?: drawable.ic_default_category,
        contentDescription = "",
    )
}

@Composable
private fun BookmarkButton(
    isBookmarked: Boolean = false,
    onBookmarkButtonClicked: () -> Unit = { }
) {
    IconButton(
        modifier = Modifier
            .size(68.dp)
            .padding(18.dp),
        onClick = { onBookmarkButtonClicked() }) {
        Icon(
            painterResource(id = drawable.ic_bookmark),
            tint = if (isBookmarked) Gs_Black else Gs_G5,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun DailyVoteDetailCardPreview() {
    VoteDetailCard(
        selectedOptionIndex = listOf(1, 2),
        isBookmarked = true,
        onBookmarkButtonClicked = { },
        onSelectOption = { },
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
                ),
                VoteOption(
                    voteOptionId = 3,
                    content = "option3",
                    count = 300
                )
            ),
            category = Category(
                name = "카테고리",
                textColor = "000000"
            )
        )
    )
}