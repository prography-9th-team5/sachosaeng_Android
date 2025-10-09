package com.example.sachosaeng.core.ui.component.tooltip

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.R

@Composable
fun SachosaengTextTooltip(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,

) {
    Box {
        Image(painter = painterResource(R.drawable.tooltip_text), contentDescription = "")
        Text(
            modifier = modifier.align(Alignment.TopCenter).padding(top = 4.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            text = text,
            color = color
        )
    }
}