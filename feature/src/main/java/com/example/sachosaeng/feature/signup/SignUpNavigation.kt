package com.example.sachosaeng.feature.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val ROUTE_SIGNUP = "signup"
const val GRAPH_SIGNUP = "signup_graph"


fun NavGraphBuilder.addSignUpNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ROUTE_SIGNUP,
        route = GRAPH_SIGNUP
    ) {
        composable(ROUTE_SIGNUP) {
            SelectUserTypeScreen()
        }
    }
}