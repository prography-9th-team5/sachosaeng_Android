package com.sachosaeng.app.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G3
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.util.extension.IntExtension.toNumberOfPeople
import com.sachosaeng.app.core.util.extension.StringExtension.toColorResource

@Composable
fun VoteSmallCard(
    modifier: Modifier = Modifier,
    isVoted: Boolean = false,
    text: String,
    backgroundColorCode: String,
    iconUrl: String? = null,
    voteCount: Int? = null,
) {
    val backgroundColorCode = if (isVoted) Gs_G3 else Color(backgroundColorCode.toColorResource())

    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = backgroundColorCode
        ),
        modifier = modifier.size(width = 156.dp, height = 176.dp)
    ) {
        Box(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Column {
                Row {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier.width(176.dp)
                    ) {
                        voteCount?.let {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                               if(isVoted) Image(
                                    painter = painterResource(R.drawable.ic_circle_check_gs_g6),
                                    contentDescription = null,
                                )
                                Text(
                                    text = voteCount.toNumberOfPeople(),
                                    color = Gs_G6,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W500
                                )
                            }
                        }
                        Text(
                            text = text,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp, fontWeight = FontWeight.W600, color = Gs_Black
                        )
                    }
                }
            }
            iconUrl?.let {
                AsyncImage(
                    contentDescription = "", model = iconUrl,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }
    }
}

@Composable
@Preview
fun VoteSmallCardPreview() {
    VoteSmallCard(
        text = "투표 제목투표 제목투표 제목투표 ",
        iconUrl = "https://picsum.photos/200/300",
        voteCount = 20,
        backgroundColorCode = "#000000",
        isVoted = true
    )
}