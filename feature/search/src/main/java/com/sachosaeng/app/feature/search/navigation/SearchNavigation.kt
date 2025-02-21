package com.sachosaeng.app.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

private const val SEARCH = "search"
const val GRAPH_SEARCH = "searchGraph"

fun NavController.navigateToSearch() {
    navigate(SEARCH)
}

fun NavGraphBuilder.addMyPageNavGraph(
    navController: NavHostController,
    navigateToWebView: (String) -> Unit,
    navigateToOpenSource: () -> Unit = {},
    snackBarMessage: (String) -> Unit = {}
) {
    navigation(
        route = GRAPH_SEARCH,
        startDestination = ROUTE_MY_PAGE
    ) {
        composable(ROUTE_MY_PAGE) {
            MyPageScreen(
                navigateToModifyCategory = { navController.navigate(MODIFY_CATEGORY) },
                navigateToPrivacyPolicy = { navigateToWebView(PRIVACY_POLICY) },
                navigateToTermsOfService = { navigateToWebView(TERMS_OF_SERVICE) },
                navigateToUserInfoModify = { navController.navigate(MODIFY_USER_INFO) },
                navigateToFaq = { navigateToWebView(FAQ) },
                navigateToRequestToAdmin = { navigateToWebView(REQUEST_TO_ADMIN) },
                navigateToOpenSource = { navigateToOpenSource() },
                navigateToSuggestVoteHistory = { navController.navigate(SUGGEST_VOTE_HISTORY) },
            )
        }
        composable(
            route = "$ROUTE_WITHDRAW?$USER_NAME={$USER_NAME}",
            arguments = listOf(
                navArgument(USER_NAME) {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = ""
                },
            )
        ) {
            WithdrawScreen(
                navigateToBackStack = { navController.popBackStack() },
                snackBarMessage = snackBarMessage
            )
        }
        composable(MODIFY_USER_INFO) {
            ModifyUserInfoScreen(
                snackBarMessage = snackBarMessage,
                navigateToBackStack = { navController.popBackStack() },
                navigateToWithdrawScreen = { userName -> navController.navigateToWithdraw(userName = userName) }
            )
        }
        composable(MODIFY_CATEGORY) {
            ModifyCategoryScreen(
                showSnackBar = snackBarMessage,
                navigateToBackStack = { navController.popBackStack() }
            )
        }
        composable(SUGGEST_VOTE_HISTORY) {
            HistoryOfSuggestedVoteScreen(
                navigateToBackStack = { navController.popBackStack() }
            )
        }
    }
}