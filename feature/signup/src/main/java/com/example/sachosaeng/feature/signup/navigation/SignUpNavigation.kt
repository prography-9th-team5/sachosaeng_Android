package com.example.sachosaeng.feature.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.sachosaeng.feature.signup.BuildConfig
import com.example.sachosaeng.feature.signup.gettermsagree.TermsOfServiceScreen
import com.example.sachosaeng.feature.signup.selectcategory.SelectCategoryScreen
import com.example.sachosaeng.feature.signup.selectusertype.SelectUserTypeScreen
import com.example.sachosaeng.feature.signup.signupcomplete.SignUpCompleteScreen

const val ROUTE_SIGNUP = "signup"
const val GRAPH_SIGNUP = "signup_graph"
const val SELECT_USER_TYPE = "selectUserType"
const val SELECT_CATEGORY = "selectCategory"
const val SIGNUP_COMPLETE = "signupComplete"
const val SIGNUP_SELECT_USER_TYPE_DEEP_LINK = "app://${BuildConfig.APP_URL}/$SELECT_USER_TYPE"

fun NavGraphBuilder.addSignUpNavGraph(
    navigateToMain: () -> Unit,
    navigateToAuth: () -> Unit,
    snackBarMessage: (String) -> Unit = {},
    navController: NavHostController
) {
    navigation(
        startDestination = ROUTE_SIGNUP,
        route = GRAPH_SIGNUP
    ) {
        composable(ROUTE_SIGNUP) {
            TermsOfServiceScreen(navigateToSocialLogin = { navigateToAuth() })
        }
        composable(
            route = SELECT_USER_TYPE,
            deepLinks = listOf(navDeepLink { uriPattern = SIGNUP_SELECT_USER_TYPE_DEEP_LINK })
        ) {
            SelectUserTypeScreen(moveToNextStep = { navController.navigate(SELECT_CATEGORY) })
        }
        composable(SELECT_CATEGORY) {
            SelectCategoryScreen(
                moveToNextStep = {
                    navController.navigate(SIGNUP_COMPLETE)
                },
                navigateToBackStack = { navController.popBackStack() },
                snackBarMessage = snackBarMessage
            )
        }
        composable(SIGNUP_COMPLETE) {
            SignUpCompleteScreen(navigateToMain = { navigateToMain() })
        }
    }
}