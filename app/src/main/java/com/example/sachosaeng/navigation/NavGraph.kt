package com.example.sachosaeng.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sachosaeng.feature.home.HomeScreen
import com.example.sachosaeng.feature.mypage.GRAPH_MY_PAGE
import com.example.sachosaeng.feature.mypage.MyPageScreen
import com.example.sachosaeng.feature.mypage.ROUTE_MY_PAGE
import com.example.sachosaeng.feature.mypage.addMyPageNavGraph
import com.example.sachosaeng.feature.splash.ROUTE_SPLASH
import com.example.sachosaeng.feature.splash.SplashScreen
import com.example.sachosaeng.feature.splash.addSplashNavGraph
import com.example.sachosaeng.feature.vote.VoteScreen

@Composable
internal fun addNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SPLASH,
    ) {
        addSplashNavGraph(navigateToMain = { navController.navigate(GRAPH_MAIN) })
        addBottomNavGraph(navController = navController)
        addMyPageNavGraph(navController = navController)
    }
}

private const val GRAPH_MAIN = "main"

fun NavGraphBuilder.addBottomNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.HOME.route,
        route = GRAPH_MAIN
    ) {
        composable(Screen.HOME.route) {
            HomeScreen(moveToMyPage = { navController.navigate(GRAPH_MY_PAGE) })
        }
        composable(Screen.VOTE.route) {
            VoteScreen(navigateToBackStack = { navController.popBackStack() })
        }
    }
}
