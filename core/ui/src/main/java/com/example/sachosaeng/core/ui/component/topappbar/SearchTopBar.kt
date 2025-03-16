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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    value: String = "",
    placeholder: String = "",
    onClear: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    navigateToBackStack: () -> Unit
) {
    var maxWidth = remember { 0 }
    val offsetX = remember { Animatable(100f) }
    val alphaAnim = remember { Animatable(0f) }
    val widthAnim = remember { Animatable(0f) }
    val animationValue = 800

    LaunchedEffect(maxWidth) {
        if (maxWidth > 0) {
            listOf(
                launch {
                    offsetX.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(animationValue, easing = FastOutSlowInEasing)
                    )
                },
                launch {
                    widthAnim.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(animationValue, easing = FastOutSlowInEasing)
                    )
                },
                launch {
                    alphaAnim.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(animationValue, easing = LinearEasing)
                    )
                }
            ).forEach { it.join() }
        }
    }

    Row(
        modifier = modifier
            .onSizeChanged { maxWidth = it.width }
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .clickable { navigateToBackStack() },
            painter = painterResource(id = R.drawable.ic_go_back),
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White, RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SachosaengSearchTextField(
                modifier = Modifier
                    .fillMaxWidth(widthAnim.value)
                    .alpha(alphaAnim.value),
                onClear = onClear,
                value = value,
                onValueChange = onValueChange,
                onSearch = onSearch,
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