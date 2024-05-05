package com.example.sachosaeng.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sachosaeng.feature.home.HomeScreen
import com.example.sachosaeng.feature.auth.AuthActivitiy

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LOGIN.route
    ) {
        composable(Screen.HOME.route) {
            HomeScreen()
        }
    }
}
