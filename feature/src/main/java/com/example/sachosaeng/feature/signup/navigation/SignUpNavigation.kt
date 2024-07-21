package com.example.sachosaeng.feature.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sachosaeng.feature.signup.selectcategory.SelectCategoryScreen
import com.example.sachosaeng.feature.signup.selectusertype.SelectUserTypeScreen

const val ROUTE_SIGNUP = "signup"
const val GRAPH_SIGNUP = "signup_graph"
const val SELECT_CATEGORY = "selectCategory"


fun NavGraphBuilder.addSignUpNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ROUTE_SIGNUP,
        route = GRAPH_SIGNUP
    ) {
        composable(ROUTE_SIGNUP) {
            SelectUserTypeScreen(moveToNextStep = { navController.navigate(SELECT_CATEGORY) })
        }
        composable(SELECT_CATEGORY) {
            SelectCategoryScreen(moveToNextStep = { navController.navigate(ROUTE_SIGNUP) })
        }
    }
}