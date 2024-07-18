package com.example.sachosaeng.feature.signup

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.feature.R
import kotlinx.coroutines.launch


@Composable
fun SignUpProgressBar() {
    val boxWidthPx = remember { mutableStateOf(0f) }
    val animateFloat = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val progressWidthPercent =  2.4

    LaunchedEffect(boxWidthPx.value) {
        if (boxWidthPx.value > 0) {
            scope.launch {
                animateFloat.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 1200, // Increase the duration for slower animation
                        easing = LinearEasing
                    )
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .onSizeChanged { size ->
                boxWidthPx.value = size.width.toFloat()
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            val lineEndPx = animateFloat.value * boxWidthPx.value / progressWidthPercent
            drawLine(
                color = Gs_Black,
                start = Offset(0f, size.height),
                end = Offset(lineEndPx.toFloat(), size.height),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Butt
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_progressbar),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .offset {
                    IntOffset(
                        x = ((animateFloat.value * boxWidthPx.value / progressWidthPercent).toInt()),
                        y = 0
                    )
                }
                .align(Alignment.CenterStart)
        )
    }
}