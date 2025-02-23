package com.sachosaeng.app.core.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.ui.component.textfield.SachosaengSearchTextField
import com.sachosaeng.app.core.ui.R
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    navigateToBackStack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var maxWidth = remember { 0 }
    val offsetX = remember { Animatable(100f) }
    val alphaAnim = remember { Animatable(0f) }
    val widthAnim = remember { Animatable(0f) }

    LaunchedEffect(maxWidth > 0) {
        scope.launch {
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = FastOutSlowInEasing
                )
            )
        }
        scope.launch {
            widthAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = FastOutSlowInEasing
                )
            )
        }
        scope.launch {
            alphaAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = LinearEasing
                )
            )
        }
    }

    Row(
        modifier = modifier
            .onSizeChanged {
                maxWidth = it.width
            }
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .clickable { navigateToBackStack() }
                .background(Color.Transparent),
            painter = painterResource(id = R.drawable.ic_go_back),
            contentDescription = null
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(White, RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SachosaengSearchTextField(
                modifier = modifier
                    .fillMaxWidth(widthAnim.value)
                    .alpha(alphaAnim.value),
                placeholder = placeholder,
            )
        }
    }
}

@Composable
@Preview
fun SearchTopBarPreview() {
    SearchTopBar(navigateToBackStack = {})
}