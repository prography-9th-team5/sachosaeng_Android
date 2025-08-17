package com.example.sachosaeng.core.ui.component.tooltip

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider
import com.sachosaeng.app.core.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SachosaengTooltipWrapper(
    message: String,
    state: TooltipState?,
    positionProvider: PopupPositionProvider,
    content: @Composable () -> Unit
) {
    if (state != null && state.isVisible) {
        TooltipBox(
            state = state,
            tooltip = {
                SachosaengTextTooltip(
                    text = message,
                )
            },
            positionProvider = positionProvider,
            content = content
        )
    } else {
        content()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SachosaengTooltipPreview() {
    val tooltipState = rememberTooltipState(isPersistent = true, initialIsVisible = true)

    SachosaengTooltipWrapper(
        message = stringResource(R.string.levelup_tooltip),
        state = tooltipState,
        positionProvider = object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize
            ): IntOffset {
                return IntOffset(0, anchorBounds.bottom + 8)
            }
        }
    ) {
    }
}