package com.example.sachosaeng.feature.util.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_G6
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.util.extension.IntExtension.toNumberOfPeople
import com.example.sachosaeng.feature.home.VoteInfo

@Composable
fun VoteColumnByCategory(modifier: Modifier = Modifier, voteList: List<VoteInfo>) {
    Column(
        modifier = Modifier.padding(top = 14.dp, bottom = 36.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        voteList.forEachIndexed { index, vote ->
            Card(
                colors = CardDefaults.cardColors().copy(
                    containerColor = Gs_White
                ),
                border = BorderStroke(1.dp, Gs_G3),
            ) {
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
}

@Composable
fun VoteCard(
    modifier: Modifier = Modifier,
    text: String,
    iconUrl: String,
    voteCount: Int,
    ranking: Int
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = ranking.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Gs_Black,
            modifier = Modifier.padding(end = 8.dp)
        )
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 6.dp),
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp, fontWeight = FontWeight.W600, color = Gs_Black
            )
            Text(
                text = voteCount.toNumberOfPeople(),
                color = Gs_G6,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
        }
        AsyncImage(
            alignment = Alignment.CenterEnd,
            contentDescription = "", model = iconUrl,
            modifier = Modifier
                .size(36.dp)
                .weight(1f)
        )
    }
}
