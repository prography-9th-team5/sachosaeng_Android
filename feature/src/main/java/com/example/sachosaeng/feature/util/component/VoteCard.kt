package com.example.sachosaeng.feature.util.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun VoteCard(modifier: Modifier = Modifier, voteList: List<VoteInfo>, category: String) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Gs_White
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
        border = BorderStroke(1.dp, Gs_G3),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            CategoryChip(Gs_G3, Gs_Black, category)
            Spacer(modifier = Modifier.size(24.dp))
            voteList.forEachIndexed { index, vote ->
                VoteText(vote.title, vote.imageUrl, vote.voteCount, index + 1)
            }
        }
    }
}

@Composable
fun VoteText(text: String, iconUrl: String, voteCount: Int, ranking: Int) {
    Row {
        Column {
            Row {
                Text(
                    text = ranking.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    color = Gs_Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(0.5f),
                    text = text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 20.sp, fontWeight = FontWeight.W600, color = Gs_Black
                )
            }
            Text(
                text = voteCount.toNumberOfPeople(),
                color = Gs_G6,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
        }
        AsyncImage(modifier = Modifier.size(36.dp), model = iconUrl, contentDescription = "")
    }
}