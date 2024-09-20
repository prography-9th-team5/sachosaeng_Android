package com.example.sachosaeng.feature.vote.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.model.SimilarArticle
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_White

@Composable
fun ArticleRow(modifier: Modifier = Modifier, similarArticle: SimilarArticle) {
    Box(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, top = 6.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Gs_White)
    ) {
        Text(
            text = similarArticle.title,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp,
                end = 40.dp
            ),
            fontSize = 15.sp,
            overflow = TextOverflow.Ellipsis,
            color = Gs_Black,
            fontWeight = FontWeight.W600
        )
    }
}
