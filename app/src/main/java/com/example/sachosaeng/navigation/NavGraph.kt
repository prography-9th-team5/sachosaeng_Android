package com.sachosaeng.app.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.util.constant.NavigationConstant.Main.MAIN_DEEP_LINK
import com.sachosaeng.app.core.util.constant.NavigationConstant.Main.ROUTE_MAIN
import com.sachosaeng.app.feature.article.navigation.addArticleGraph
import com.sachosaeng.app.feature.article.navigation.navigateToArticleDetail
import com.sachosaeng.app.feature.auth.navigation.navigationToAuth
import com.sachosaeng.app.feature.bookmark.navigation.addBookmarkGraph
import com.sachosaeng.app.feature.home.HomeScreen
import com.sachosaeng.app.feature.mypage.navigation.GRAPH_MY_PAGE
import com.sachosaeng.app.feature.mypage.navigation.addMyPageNavGraph
import com.sachosaeng.app.feature.signup.navigation.GRAPH_SIGNUP
import com.sachosaeng.app.feature.signup.navigation.addSignUpNavGraph
import com.sachosaeng.app.feature.splash.ROUTE_SPLASH
import com.sachosaeng.app.feature.splash.addSplashNavGraph
import com.sachosaeng.app.feature.vote.navigation.addVoteGraph
import com.sachosaeng.app.feature.vote.navigation.navigateToVoteDetail
import com.sachosaeng.app.feature.webview.addWebViewScreen
import com.sachosaeng.app.feature.webview.navigateToWebView
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
            navigateToLogin = { navController.navigationToAuth() }
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
            navigateToMyPage = { navController.navigate(GRAPH_MY_PAGE) },
            showSnackBar = snackBarMessage
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