package com.example.sachosaeng.feature.vote.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G3

@Composable
fun VoteCompleteInfo(
    modifier: Modifier = Modifier,
    completeDescription: String,
    completeDescriptionIconRes: Int?
) {
    Column {
        Row(
            modifier = modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = completeDescriptionIconRes ?: R.drawable.ic_vote_complete_etc),
                contentDescription = "",
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = completeDescription,
                color = Gs_Black,
                fontWeight = FontWeight.W700,
                fontSize = 14.sp
            )
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .background(Gs_G3)
                .fillMaxWidth()
                .height(16.dp)
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.article_label),
            color = Gs_Black,
            fontWeight = FontWeight.W700,
            fontSize = 18.sp
        )
        ArticleRow()
    }
}