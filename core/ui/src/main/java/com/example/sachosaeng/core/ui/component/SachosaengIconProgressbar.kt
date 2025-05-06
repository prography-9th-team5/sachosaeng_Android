package com.sachosaeng.app.feature.signup.component.com.example.sachosaeng.core.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G3
import com.sachosaeng.app.core.ui.R.drawable
import kotlinx.coroutines.launch

@Composable
fun SachosaengIconProgressbar(
    lineColor: Color = Gs_Black,
    targetValue: Float = 1f,
    modifier: Modifier = Modifier
) {
    val boxWidthPx = remember { mutableFloatStateOf(0f) }
    val animateFloat = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(boxWidthPx.floatValue) {
        if (boxWidthPx.floatValue > 0) {
            scope.launch {
                animateFloat.animateTo(
                    targetValue = targetValue,
                    animationSpec = tween(
                        durationMillis = 1200,
                        easing = LinearEasing
                    )
                )
            }
        }
    }
    Box(
        modifier = modifier
            .height(60.dp)
            .onSizeChanged { size ->
                boxWidthPx.floatValue = size.width.toFloat()
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            val lineEndPx = animateFloat.value * boxWidthPx.floatValue
            drawLine(
                color = Gs_G3,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 8.dp.toPx(),
                cap = StrokeCap.Round
            )
            drawLine(
                color = lineColor,
                start = Offset(0f, size.height),
                end = Offset(lineEndPx, size.height),
                strokeWidth = 8.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
        Image(
            painter = painterResource(id = drawable.ic_progressbar),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .offset {
                    IntOffset(
                        x = ((animateFloat.value * boxWidthPx.value - 70).toInt()),
                        y = 0
                    )
                }
                .align(Alignment.CenterStart)
        )
    }
}