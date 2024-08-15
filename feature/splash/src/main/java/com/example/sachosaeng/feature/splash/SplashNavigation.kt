package com.example.sachosaeng.feature.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SPLASH = "splash"

fun NavGraphBuilder.addSplashNavGraph(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    composable(com.example.sachosaeng.feature.splash.ROUTE_SPLASH) {
        SplashScreen(
            navigateToMain = navigateToMain,
            navigateToSignUp = navigateToSignUp
        )
    }
}