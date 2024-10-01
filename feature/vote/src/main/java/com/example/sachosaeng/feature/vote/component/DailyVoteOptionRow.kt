package com.sachosaeng.app.feature.vote.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G3
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.ui.R.drawable
import com.sachosaeng.app.core.ui.theme.Gs_G6

@Composable
fun DailyVoteOptionRow(
    modifier: Modifier = Modifier,
    text: String,
    isSeleceted: Boolean,
    isVoted: Boolean,
    onSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(if (isSeleceted) Gs_G6 else Gs_G2)
            .fillMaxWidth()
            .padding(
                start = 16.dp, top = 16.dp, bottom = 16.dp, end = 40.dp
            )
            .clickable {
                if (!isVoted) onSelected(text)
            }
    ) {
        Image(painter = painterResource(id = drawable.ic_unchecked), contentDescription = null)
        Text(
            text = text,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            color = if (isSeleceted) Gs_White else Gs_Black,
            fontWeight = if (isSeleceted) FontWeight.W700 else FontWeight.W500
        )
    }
}