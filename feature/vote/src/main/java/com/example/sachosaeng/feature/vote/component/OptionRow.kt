package com.example.sachosaeng.feature.vote.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_G4
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White
import kotlinx.coroutines.launch

@Composable
fun OptionRow(
    modifier: Modifier = Modifier,
    text: String,
    isSeleceted: Boolean,
    isVoted: Boolean,
    optionPercentage: Float,
    onSelected: (String) -> Unit
) {
    var largestSize by remember { mutableStateOf(IntSize.Zero) }
    val normalizedPercentage = (optionPercentage / 100f).coerceIn(0f, 1f)
    val animateFloat = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(largestSize.width) {
        if (largestSize.width > 0) {
            scope.launch {
                animateFloat.animateTo(
                    targetValue = normalizedPercentage,
                    animationSpec = tween(
                        durationMillis = 800,
                        easing = LinearEasing
                    )
                )
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged { size ->
                largestSize = size
            }
            .border(
                width = 1.dp,
                color = if (isSeleceted) Gs_Black else Gs_G3,
                shape = RoundedCornerShape(4.dp)
            )
            .clip(RoundedCornerShape(4.dp))
            .background(if (isVoted && isSeleceted) Gs_G5 else Gs_G2)
            .clickable {
                if (!isVoted) onSelected(text)
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val lineEndPx = animateFloat.value * size.width
            drawLine(
                color = if(isSeleceted) Gs_Black else Gs_G4,
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
            color = if (isVoted && isSeleceted) Gs_White else Gs_Black,
            fontWeight = if (isSeleceted) FontWeight.W700 else FontWeight.W500
        )
        Text(
            text = "${optionPercentage.toInt()}%",
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterEnd),
            fontSize = 14.sp,
            color = if (isVoted && isSeleceted) Gs_White else Gs_Black,
            fontWeight = if (isSeleceted) FontWeight.W700 else FontWeight.W500
        )
    }
}