package com.example.sachosaeng.core.ui.component.searchbar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.core.ui.component.textfield.SachosaengSearchTextField
import kotlinx.coroutines.launch

@Composable
fun ExpandableSearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    onClick: () -> Unit = {},
) {
    val scope = rememberCoroutineScope()

    val widthAnim = remember { Animatable(0f) }
    val alphaAnim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        scope.launch {
            widthAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
            alphaAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 130, easing = LinearEasing)
            )
        }
    }

    Box(
        modifier = modifier
            .height(50.dp)
    ) {
        Row(
            modifier = modifier
                .width((widthAnim.value * 280).dp)
                .clip(RoundedCornerShape(20.dp))
                .background(White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SachosaengSearchTextField(
                modifier = modifier.alpha(alphaAnim.value),
                placeholder = placeholder,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchButtonPreview() {
  //  ExpandableSearchBar()
}