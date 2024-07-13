package com.example.sachosaeng.feature.mypage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val GRAPH_MY_PAGE = "myPageGraph"
const val ROUTE_MY_PAGE = "myPage"
fun NavGraphBuilder.addMyPageNavGraph(navController: NavHostController) {
    navigation(
        route = ROUTE_MY_PAGE,
        startDestination = GRAPH_MY_PAGE
    ) {
        composable(GRAPH_MY_PAGE) {
            MyPageScreen(navigateToBackStack = { navController.popBackStack() })
        }
    }
}