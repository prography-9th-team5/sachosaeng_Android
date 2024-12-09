package com.sachosaeng.app.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.sachosaeng.app.core.ui.theme.Gs_White
import org.orbitmvi.orbit.compose.collectSideEffect


@Composable
fun SplashScreen(
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    viewModel.collectSideEffect {
        when (it) {
            is SplashSideEffect.NavigateToLogin -> navigateToLogin()
            is SplashSideEffect.NavigateToHome -> navigateToMain()
            else -> {}
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Gs_White),
    ) {
        val (title, image) = createRefs()
        Image(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                bottom.linkTo(image.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(id = R.drawable.ic_title),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(image) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = null
        )
    }
}