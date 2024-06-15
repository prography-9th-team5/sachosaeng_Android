package com.example.sachosaeng.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sachosaeng.feature.home.HomeScreen
import com.example.sachosaeng.feature.auth.AuthActivitiy
import com.example.sachosaeng.feature.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SPLASH.route
    ) {
        composable(Screen.SPLASH.route) {
           SplashScreen(navController)
        }
        composable(Screen.HOME.route) {
            HomeScreen()
        }
    }
}
