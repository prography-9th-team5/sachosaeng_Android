package com.sachosaeng.app.feature.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.sachosaeng.app.core.util.constant.NavigationConstant.SignUp.ROUTE_SIGNUP
import com.sachosaeng.app.core.util.constant.NavigationConstant.SignUp.SELECT_USER_TYPE
import com.sachosaeng.app.core.util.constant.NavigationConstant.SignUp.SIGNUP_DEEP_LINK
import com.sachosaeng.app.feature.signup.gettermsagree.TermsOfServiceScreen
import com.sachosaeng.app.feature.signup.selectcategory.SelectCategoryScreen
import com.sachosaeng.app.feature.signup.selectusertype.SelectUserTypeScreen
import com.sachosaeng.app.feature.signup.signupcomplete.SignUpCompleteScreen
import com.sachosaeng.app.feature.webview.WebViewUrl.PRIVACY_POLICY
import com.sachosaeng.app.feature.webview.WebViewUrl.TERMS_OF_SERVICE
import com.sachosaeng.app.feature.webview.navigateToWebView

const val GRAPH_SIGNUP = "signup_graph"
const val SELECT_CATEGORY = "selectCategory"
const val SIGNUP_COMPLETE = "signupComplete"

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
        composable(
            route = ROUTE_SIGNUP,
            deepLinks = listOf(navDeepLink { uriPattern = SIGNUP_DEEP_LINK })
        ) {
            TermsOfServiceScreen(
                navigateToSelectUserType = {
                    navController.navigate(SELECT_USER_TYPE) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true 
                    }
                },
                navigateToServiceTermsDetail = { navController.navigateToWebView(TERMS_OF_SERVICE) },
                navigateToPersonalInformationTerm = { navController.navigateToWebView(PRIVACY_POLICY) })
        }
        composable(SELECT_USER_TYPE) {
            SelectUserTypeScreen(moveToNextStep = { navController.navigate(SELECT_CATEGORY) })
        }
        composable(SELECT_CATEGORY) {
            SelectCategoryScreen(
                moveToNextStep = {
                    navController.navigate(SIGNUP_COMPLETE)
                },
                navigateToAuth = navigateToAuth,
                navigateToBackStack = { navController.popBackStack() },
                snackBarMessage = snackBarMessage
            )
        }
        composable(SIGNUP_COMPLETE) {
            SignUpCompleteScreen(navigateToMain = { navigateToMain() })
        }
    }
}