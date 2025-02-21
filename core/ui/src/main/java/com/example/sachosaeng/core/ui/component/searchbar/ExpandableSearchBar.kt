package com.example.sachosaeng.core.ui.component.searchbar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.component.textfield.SachosaengSearchTextField
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.noRippleClickable
import com.sachosaeng.app.core.ui.theme.Gs_G5
import kotlinx.coroutines.launch

@Composable
fun ExpandableSearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val isExpanded = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val widthAnim = remember { Animatable(0f) }
    val alphaAnim = remember { Animatable(0f) }

    LaunchedEffect(isExpanded.value) {
        scope.launch {
            if (isExpanded.value) {
                widthAnim.animateTo(1f, animationSpec = tween(500))
                alphaAnim.animateTo(1f, animationSpec = tween(500))
            }
        }
    }

    Box(
        modifier = modifier
            .height(50.dp)
    ) {
        Row(
            modifier = Modifier
                .width((widthAnim.value * 280).dp)
                .clip(RoundedCornerShape(20.dp))
                .background(White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isExpanded.value) {
                SachosaengSearchTextField(
                    modifier = Modifier.alpha(alphaAnim.value)
                )
            }
        }
        if (!isExpanded.value) {
            SearchButton(
                onClick = {
                    onClick()
                    isExpanded.value = true
                }
            )
        }
    }
}


@Composable
private fun SearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = White)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .noRippleClickable {
                onClick()
            }
    ) {
        Text(
            color = Gs_G5,
            text = stringResource(id = R.string.search_button_label),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            modifier = modifier.noRippleClickable { onClick() }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchButtonPreview() {
    ExpandableSearchButton()
}