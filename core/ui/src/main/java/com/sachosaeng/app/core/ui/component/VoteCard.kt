package com.sachosaeng.app.core.ui.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sachosaeng.app.core.ui.R.drawable
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G3
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.util.extension.IntExtension.toNumberOfPeople
import com.sachosaeng.app.core.util.extension.StringExtension.toColorResource

@Composable
fun VoteCard(
    modifier: Modifier = Modifier,
    isVoted: Boolean = false,
    text: String,
    backgroundColorCode: String,
    iconUrl: String? = null,
    voteCount: Int? = null,
    ranking: Int,
    rankingTextVisibility: Boolean = false
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = if (isVoted) Gs_G3 else Gs_White
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isVoted) Image(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        contentDescription = "",
                        painter = painterResource(id = drawable.ic_checked_circle),
                        alignment = Alignment.CenterStart
                    )
                    if (rankingTextVisibility) Text(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        textAlign = TextAlign.Center,
                        text = ranking.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600,
                        color = Gs_Black,
                    )
                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            text = text,
                            maxLines = 2,
                            overflow = TextOverflow.Visible,
                            fontSize = 15.sp, fontWeight = FontWeight.W600, color = Gs_Black
                        )
                        voteCount?.let {
                            Text(
                                modifier = Modifier.padding(top = 6.dp),
                                text = voteCount.toNumberOfPeople(),
                                color = Gs_G6,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
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
        text = "투표 제목투표 제목투표 제목투표 ",
        iconUrl = "https://picsum.photos/200/300",
        voteCount = null,
        ranking = 1,
        rankingTextVisibility = true,
        backgroundColorCode = "#000000",
        isVoted = true
    )
}