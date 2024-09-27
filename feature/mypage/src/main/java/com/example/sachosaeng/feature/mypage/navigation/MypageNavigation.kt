package com.example.sachosaeng.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.sachosaeng.core.util.extension.StringExtension.urlEncode
import com.example.sachosaeng.feature.mypage.main.MyPageScreen
import com.example.sachosaeng.feature.mypage.modify.ModifyUserInfoScreen
import com.example.sachosaeng.feature.mypage.withdraw.WithdrawScreen
import com.example.sachosaeng.feature.webview.WebViewUrl.FAQ
import com.example.sachosaeng.feature.webview.WebViewUrl.PRIVACY_POLICY
import com.example.sachosaeng.feature.webview.WebViewUrl.REQUEST_TO_ADMIN
import com.example.sachosaeng.feature.webview.WebViewUrl.TERMS_OF_SERVICE

const val GRAPH_MY_PAGE = "myPageGraph"
const val ROUTE_MY_PAGE = "myPage"
private const val ROUTE_WITHDRAW = "withdraw"
const val USER_NAME = "userName"
private const val MODIFY_USER_INFO = "modifyUserInfo"

private fun NavController.navigateToWithdraw(userName: String) {
    val encodedUrl = userName.urlEncode()
    navigate("$ROUTE_WITHDRAW?$USER_NAME=$encodedUrl")
}

fun NavGraphBuilder.addMyPageNavGraph(
    navController: NavHostController,
    navigateToWebView: (String) -> Unit,
    navigateToOpenSource: () -> Unit = {},
    snackBarMessage: (String) -> Unit = {}
) {
    navigation(
        route = ROUTE_MY_PAGE,
        startDestination = GRAPH_MY_PAGE
    ) {
        composable(GRAPH_MY_PAGE) {
            MyPageScreen(
                navigateToBackStack = { navController.popBackStack() },
                navigateToPrivacyPolicy = { navigateToWebView(PRIVACY_POLICY) },
                navigateToTermsOfService = { navigateToWebView(TERMS_OF_SERVICE) },
                navigateToUserInfoModify = { navController.navigate(MODIFY_USER_INFO) },
                navigateToFaq = { navigateToWebView(FAQ) },
                navigateToRequestToAdmin = { navigateToWebView(REQUEST_TO_ADMIN) },
                navigateToOpenSource = { navigateToOpenSource() },
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
            )) {
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
    }
}