package com.example.sachosaeng.core.ui.component.card

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.util.extension.StringExtension.toColorResource
import kotlinx.coroutines.launch

@Composable
fun HomeDailyVoteOptionRow(
    modifier: Modifier = Modifier,
    text: String,
    isSeleceted: Boolean,
    optionPercentage: Float = 0f,
    percentageColorRes: String = "FFFFFF",
    percentageTextColorRes: Color = White,
) {

    var largestSize by remember { mutableStateOf(IntSize.Zero) }
    val normalizedPercentage = (optionPercentage / 100f).coerceIn(0f, 1f)
    val animateFloat = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(largestSize.width) {
        if (largestSize.width > 0 && isSeleceted) {
            scope.launch {
                animateFloat.animateTo(
                    targetValue = normalizedPercentage,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearEasing
                    )
                )
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged { size -> largestSize = size }
            .clip(RoundedCornerShape(4.dp))
            .background(Gs_G6)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val lineEndPx = animateFloat.value * size.width
            //todo: 더 효율적인 방법 없는지 찾아보기
            if (isSeleceted) drawLine(
                color = White,
                start = Offset(0f, size.height),
                end = Offset(lineEndPx.coerceIn(0f, size.width), size.height),
                strokeWidth = largestSize.height.toFloat().dp.toPx(),
            )
            drawLine(
                color = if (isSeleceted) Color(percentageColorRes.toColorResource()) else Gs_G5,
                start = Offset(0f, size.height),
                end = Offset(lineEndPx.coerceIn(0f, size.width), size.height),
                strokeWidth = largestSize.height.toFloat().dp.toPx(),
            )
        }
        Text(
            text = text,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 40.dp),
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            color = if (isSeleceted) percentageTextColorRes else Gs_White,
            fontWeight = if (isSeleceted) FontWeight.W700 else FontWeight.W500
        )
        Text(
            text = "${optionPercentage.toInt()}%",
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterEnd),
            fontSize = 14.sp,
            color = if (isSeleceted) percentageTextColorRes else Gs_White,
            fontWeight = if (isSeleceted) FontWeight.W700 else FontWeight.W500
        )
    }
}