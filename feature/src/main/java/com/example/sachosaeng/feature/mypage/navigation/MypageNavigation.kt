package com.example.sachosaeng.feature.mypage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sachosaeng.feature.mypage.main.MyPageScreen
import com.example.sachosaeng.feature.mypage.modify.ModifyUserInfoScreen
import com.example.sachosaeng.feature.mypage.withdraw.WithdrawScreen
import com.example.sachosaeng.feature.webview.WebViewUrl.PRIVACY_POLICY
import com.example.sachosaeng.feature.webview.WebViewUrl.TERMS_OF_SERVICE

const val GRAPH_MY_PAGE = "myPageGraph"
const val ROUTE_MY_PAGE = "myPage"
private const val WITHDRAW = "withdraw"
private const val MODIFY_USER_INFO = "modifyUserInfo"
fun NavGraphBuilder.addMyPageNavGraph(
    navController: NavHostController,
    navigateToWebView: (String) -> Unit
) {
    navigation(
        route = ROUTE_MY_PAGE,
        startDestination = GRAPH_MY_PAGE
    ) {
        composable(GRAPH_MY_PAGE) {
            MyPageScreen(
                navigateToBackStack = { navController.popBackStack() },
                navigateToWithDraw = { navController.navigate(WITHDRAW) },
                navigateToPrivacyPolicy = {
                    navigateToWebView(PRIVACY_POLICY)
                },
                navigateToTermsOfService = {
                    navigateToWebView(TERMS_OF_SERVICE)
                },
                navigateToUserInfoModify = { navController.navigate(MODIFY_USER_INFO) }
            )
        }
        composable(WITHDRAW) {
            WithdrawScreen()
        }
        composable(MODIFY_USER_INFO) {
            ModifyUserInfoScreen(
                navigateToBackStack = { navController.popBackStack() },
                navigateToWithdrawScreen = { navController.navigate(WITHDRAW) }
            )
        }
    }
}