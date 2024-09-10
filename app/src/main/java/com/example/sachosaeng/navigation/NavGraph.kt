package com.example.sachosaeng.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sachosaeng.feature.home.HomeScreen
import com.example.sachosaeng.feature.mypage.navigation.GRAPH_MY_PAGE
import com.example.sachosaeng.feature.mypage.navigation.addMyPageNavGraph
import com.example.sachosaeng.feature.signup.navigation.GRAPH_SIGNUP
import com.example.sachosaeng.feature.signup.navigation.addSignUpNavGraph
import com.example.sachosaeng.feature.splash.ROUTE_SPLASH
import com.example.sachosaeng.feature.splash.addSplashNavGraph
import com.example.sachosaeng.feature.vote.VoteScreen
import com.example.sachosaeng.feature.vote.navigation.addVoteGraph
import com.example.sachosaeng.feature.vote.navigation.navigateToVoteDetail
import com.example.sachosaeng.feature.webview.addWebViewScreen
import com.example.sachosaeng.feature.webview.navigateToWebView

@Composable
internal fun addNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SPLASH,
    ) {
        addSplashNavGraph(
            navigateToMain = { navController.navigate(GRAPH_MAIN) },
            navigateToSignUp = { navController.navigate(GRAPH_SIGNUP) }
        )
        addWebViewScreen(navController = navController)
        addSignUpNavGraph(
            navController = navController,
            navigateToMain = { navController.navigate(GRAPH_MAIN) })
        addBottomNavGraph(navController = navController)
        addMyPageNavGraph(
            navController = navController,
            navigateToWebView = { url -> navController.navigateToWebView(url) }
        )
        addVoteGraph(navController = navController)
        addBookmarkGraph(navController = navController)
    }
}

private const val GRAPH_MAIN = "mainGraph"
private const val ROUTE_MAIN = "main"

fun NavGraphBuilder.addBottomNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ROUTE_MAIN,
        route = GRAPH_MAIN
    ) {
        composable(ROUTE_MAIN) {
            HomeScreen(
                moveToMyPage = {
                    navController.navigate(
                        GRAPH_MY_PAGE
                    )
                },
                navigateToVoteCard = {
                    navController.navigateToVoteDetail(voteId = it)
                }
            )
        }
        composable(Screen.VOTE.route) {
            VoteScreen(navigateToBackStack = { navController.popBackStack() })
        }
    }
}
