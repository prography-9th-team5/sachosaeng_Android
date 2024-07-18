package com.example.sachosaeng.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.feature.R
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun SplashScreen(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val showSplashScreen = viewModel.collectAsState()

    if (showSplashScreen.value) {
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
    } else {
        //todo: 가입여부 확인 추가
//        navigateToMain()
        navigateToSignUp()
    }
}