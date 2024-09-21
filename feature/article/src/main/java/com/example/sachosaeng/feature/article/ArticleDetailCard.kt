package com.example.sachosaeng.feature.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.theme.Gs_G1
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_G6

@Composable
fun ArticleDetailCard(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    content: String,
    referenceName: String
) {
    Text(
        text = title,
        fontWeight = FontWeight.W700,
        fontSize = 22.sp
    )
    Column(
        modifier = modifier
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Gs_G1)
            .padding(16.dp)
    ) {
        if (subTitle.isNotEmpty()) Text(
            text = subTitle,
            fontSize = 16.sp,
            fontWeight = FontWeight.W700
        )
        Text(
            text = content,
            color = Gs_G6,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = referenceName,
        color = Gs_G5,
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
    )
}