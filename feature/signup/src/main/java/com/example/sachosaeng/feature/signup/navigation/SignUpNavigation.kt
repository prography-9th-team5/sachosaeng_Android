package com.example.sachosaeng.feature.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sachosaeng.feature.signup.selectcategory.SelectCategoryScreen
import com.example.sachosaeng.feature.signup.selectusertype.SelectUserTypeScreen
import com.example.sachosaeng.feature.signup.signupcomplete.SignUpCompleteScreen

const val ROUTE_SIGNUP = "signup"
const val GRAPH_SIGNUP = "signup_graph"
const val SELECT_CATEGORY = "selectCategory"
const val SIGNUP_COMPLETE = "signupComplete"


fun NavGraphBuilder.addSignUpNavGraph(
    navigateToMain: () -> Unit,
    navController: NavHostController
) {
    navigation(
        startDestination = ROUTE_SIGNUP,
        route = GRAPH_SIGNUP
    ) {
        composable(ROUTE_SIGNUP) {
            SelectUserTypeScreen(moveToNextStep = { navController.navigate(SELECT_CATEGORY) })
        }
        composable(SELECT_CATEGORY) {
            SelectCategoryScreen(moveToNextStep = {
                navController.navigate(SIGNUP_COMPLETE)
            }, navigateToBackStack = { navController.popBackStack() })
        }
        composable(SIGNUP_COMPLETE) {
            SignUpCompleteScreen(navigateToMain = { navigateToMain() })
        }
    }
}