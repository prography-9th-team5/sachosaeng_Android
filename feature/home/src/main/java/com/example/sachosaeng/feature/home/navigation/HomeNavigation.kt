package com.example.sachosaeng.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.sachosaeng.app.core.util.constant.NavigationConstant
import com.sachosaeng.app.core.util.constant.NavigationConstant.Main.ROUTE_MAIN
import com.sachosaeng.app.feature.home.HomeScreen


const val GRAPH_MAIN = "mainGraph"

fun NavController.navigateToMain() {
    navigate(GRAPH_MAIN) {
        popUpTo(currentBackStackEntry?.destination?.route.orEmpty()) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.addMainGraph(
    navigateToMyPage: () -> Unit,
    navigateToAddVote: () -> Unit,
    navigateToVoteDetail: (voteId: Int, isDailyVote: Boolean) -> Unit
) {
    navigation(
        startDestination = ROUTE_MAIN,
        route = GRAPH_MAIN
    ) {
        composable(
            route = ROUTE_MAIN,
            deepLinks = listOf(navDeepLink { uriPattern = NavigationConstant.Main.MAIN_DEEP_LINK })
        ) {
            HomeScreen(
                moveToMyPage = { navigateToMyPage() },
                navigateToAddVote = navigateToAddVote,
                navigateToVoteCard = { voteId, isDailyVote ->
                    navigateToVoteDetail(voteId, isDailyVote)
                }
            )
        }
    }
}
