package com.sachosaeng.app.feature.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SPLASH = "splash"

fun NavGraphBuilder.addSplashNavGraph(
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(ROUTE_SPLASH) {
        SplashScreen(
            navigateToMain = navigateToMain,
            navigateToLogin = navigateToLogin
        )
    }
}