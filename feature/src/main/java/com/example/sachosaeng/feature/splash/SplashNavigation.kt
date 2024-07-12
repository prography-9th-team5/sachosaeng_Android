package com.example.sachosaeng.feature.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SPLASH = "splash"

fun NavGraphBuilder.addSplashNavGraph(
    navigateToMain: () -> Unit
) {
    composable(ROUTE_SPLASH) {
        SplashScreen(
            navigateToMain = navigateToMain
        )
    }
}