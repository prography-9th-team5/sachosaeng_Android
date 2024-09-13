package com.example.sachosaeng.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G6
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.util.extension.IntExtension.toNumberOfPeople
import com.example.sachosaeng.core.util.extension.StringExtension.toColorResource

@Composable
fun VoteCard(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColorCode: String,
    iconUrl: String? = null,
    voteCount: Int? = null,
    ranking: Int,
    rankingTextVisibility: Boolean = false
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Gs_White
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row {
                    if (rankingTextVisibility) Text(
                        modifier = Modifier
                            .padding(end = 8.dp, bottom = 6.dp),
                        textAlign = TextAlign.Center,
                        text = ranking.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600,
                        color = Gs_Black,
                    )
                    Column {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(bottom = 6.dp),
                            text = text,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp, fontWeight = FontWeight.W600, color = Gs_Black
                        )
                        Text(
                            text = voteCount?.toNumberOfPeople() ?: "",
                            color = Gs_G6,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500
                        )
                    }

                }
            }
            iconUrl?.let {
                AsyncImage(
                    alignment = Alignment.CenterEnd,
                    contentDescription = "", model = iconUrl,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .size(32.dp)
                        .background(Color(backgroundColorCode.toColorResource()))
                        .padding(7.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun VoteCardPreview() {
    VoteCard(
        text = "투표 제목",
        iconUrl = "https://picsum.photos/200/300",
        voteCount = 100,
        ranking = 1,
        backgroundColorCode = "#000000"
    )
}