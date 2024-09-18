package com.example.sachosaeng.feature.vote.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.feature.vote.R

@Composable
fun VoteCompleteScreen(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.vote_complete_animation)
    )
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Gs_G2)
    ) {
        LottieAnimation(
            modifier = modifier.align(Alignment.Center),
            composition = composition,
            progress = { lottieAnimatable.progress },
            contentScale = ContentScale.FillHeight
        )
    }
}