package com.example.sachosaeng.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.util.constant.NavigationConstant.Main.MAIN_DEEP_LINK
import com.example.sachosaeng.core.util.constant.NavigationConstant.Main.ROUTE_MAIN
import com.example.sachosaeng.feature.article.navigation.addArticleGraph
import com.example.sachosaeng.feature.article.navigation.navigateToArticleDetail
import com.example.sachosaeng.feature.auth.navigation.navigationToAuth
import com.example.sachosaeng.feature.bookmark.navigation.addBookmarkGraph
import com.example.sachosaeng.feature.home.HomeScreen
import com.example.sachosaeng.feature.mypage.navigation.GRAPH_MY_PAGE
import com.example.sachosaeng.feature.mypage.navigation.addMyPageNavGraph
import com.example.sachosaeng.feature.signup.BuildConfig
import com.example.sachosaeng.feature.signup.navigation.GRAPH_SIGNUP
import com.example.sachosaeng.feature.signup.navigation.SELECT_USER_TYPE
import com.example.sachosaeng.feature.signup.navigation.addSignUpNavGraph
import com.example.sachosaeng.feature.splash.ROUTE_SPLASH
import com.example.sachosaeng.feature.splash.addSplashNavGraph
import com.example.sachosaeng.feature.vote.navigation.addVoteGraph
import com.example.sachosaeng.feature.vote.navigation.navigateToVoteDetail
import com.example.sachosaeng.feature.webview.addWebViewScreen
import com.example.sachosaeng.feature.webview.navigateToWebView
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
internal fun NavGraph(
    navController: NavHostController,
    snackBarMessage: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SPLASH,
    ) {
        addSplashNavGraph(
            navigateToMain = {
                navController.navigate(GRAPH_MAIN) {
                    popUpTo(navController.currentBackStackEntry?.destination?.route.orEmpty()) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            navigateToSignUp = {
                navController.navigate(GRAPH_SIGNUP) {
                    popUpTo(navController.currentBackStackEntry?.destination?.route.orEmpty()) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
        addWebViewScreen(navController = navController)
        addSignUpNavGraph(
            navController = navController,
            navigateToMain = { navController.navigate(GRAPH_MAIN) },
            navigateToAuth = { navController.navigationToAuth() },
            snackBarMessage = snackBarMessage
        )
        addMainGraph(navController = navController)
        addMyPageNavGraph(
            navController = navController,
            navigateToWebView = { url -> navController.navigateToWebView(url) },
            navigateToOpenSource = { navController.navigateToOpenSource() },
            snackBarMessage = snackBarMessage
        )
        addVoteGraph(
            navController = navController,
            navigateToArticleDetail = { articleId, categoryId ->
                navController.navigateToArticleDetail(
                    articleId = articleId,
                    categoryId = categoryId
                )
            },
        )
        addArticleGraph(navController = navController)
        addBookmarkGraph(
            navigateToVote = { id ->
                navController.navigateToVoteDetail(
                    voteId = id,
                    isDailyVote = false
                )
            },
            navigateToArticle = { id, categoryId ->
                navController.navigateToArticleDetail(
                    articleId = id,
                    categoryId = categoryId
                )
            },
            navigateToMyPage = { navController.navigate(GRAPH_MY_PAGE) }
        )
    }
}

const val GRAPH_MAIN = "mainGraph"

fun NavGraphBuilder.addMainGraph(navController: NavHostController) {
    navigation(
        startDestination = ROUTE_MAIN,
        route = GRAPH_MAIN
    ) {
        composable(
            route = ROUTE_MAIN,
            deepLinks = listOf(navDeepLink { uriPattern = MAIN_DEEP_LINK })
        ) {
            HomeScreen(
                moveToMyPage = {
                    navController.navigate(
                        GRAPH_MY_PAGE
                    )
                },
                navigateToVoteCard = { voteId, isDailyVote ->
                    navController.navigateToVoteDetail(voteId = voteId, isDailyVote = isDailyVote)
                }
            )
        }
    }
}


fun NavController.navigateToOpenSource() {
    val resourceProvider = context.resources
    context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
    OssLicensesMenuActivity.setActivityTitle(resourceProvider.getString(R.string.mypage_menu_open_source))
}