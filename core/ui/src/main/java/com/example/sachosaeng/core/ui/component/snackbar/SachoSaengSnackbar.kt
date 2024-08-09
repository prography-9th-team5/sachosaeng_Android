package com.example.sachosaeng.core.ui.component.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.ui.theme.Gs_G4
import com.example.sachosaeng.core.ui.theme.Gs_G6
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SachoSaengSnackbar(
    message: String,
    icon: @Composable () -> Unit = { },
    onDismiss: () -> Unit
) {
    var action by remember { mutableStateOf<Job?>(null) }
    var transition = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    LaunchedEffect(key1 = message) {
        action?.cancel()
        transition.targetState = true
        action = launch {
            delay(2000)
            transition.targetState = false
            onDismiss()
        }
    }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(bottom = 16.dp)
    )
    {
        AnimatedVisibility(
            visibleState = transition,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .background(Gs_G4)
                    .wrapContentHeight()
                    .padding(20.dp)
            ) {
                icon()
                Text(
                    text = message,
                    color = Gs_G6,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}