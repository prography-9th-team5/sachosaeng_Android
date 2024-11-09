package com.sachosaeng.app.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sachosaeng.feature.addvote.navigation.addAddVoteGraph
import com.example.sachosaeng.feature.addvote.navigation.navigateToAddVote
import com.example.sachosaeng.feature.home.navigation.addMainGraph
import com.example.sachosaeng.feature.home.navigation.navigateToMain
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.feature.article.navigation.addArticleGraph
import com.sachosaeng.app.feature.article.navigation.navigateToArticleDetail
import com.sachosaeng.app.feature.auth.navigation.navigationToAuth
import com.sachosaeng.app.feature.bookmark.navigation.addBookmarkGraph
import com.sachosaeng.app.feature.mypage.navigation.GRAPH_MY_PAGE
import com.sachosaeng.app.feature.mypage.navigation.addMyPageNavGraph
import com.sachosaeng.app.feature.signup.navigation.addSignUpNavGraph
import com.sachosaeng.app.feature.splash.ROUTE_SPLASH
import com.sachosaeng.app.feature.splash.addSplashNavGraph
import com.sachosaeng.app.feature.vote.navigation.addVoteGraph
import com.sachosaeng.app.feature.vote.navigation.navigateToVoteDetail
import com.sachosaeng.app.feature.webview.addWebViewScreen
import com.sachosaeng.app.feature.webview.navigateToWebView

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
            navigateToMain = { navController.navigateToMain() },
            navigateToLogin = { navController.navigationToAuth() }
        )
        addWebViewScreen(navController = navController)
        addSignUpNavGraph(
            navController = navController,
            navigateToMain = { navController.navigateToMain() },
            navigateToAuth = { navController.navigationToAuth() },
            snackBarMessage = snackBarMessage
        )
        addMainGraph(
            navigateToMyPage = { navController.navigate(GRAPH_MY_PAGE) },
            navigateToAddVote = { navController.navigateToAddVote() },
            navigateToVoteDetail = { id, isDailyVote ->
                navController.navigateToVoteDetail(
                    voteId = id,
                    isDailyVote = isDailyVote
                )
            }
        )
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
            navigateToArticle = { id ->
                navController.navigateToArticleDetail(
                    articleId = id,
                )
            },
            navigateToMyPage = { navController.navigate(GRAPH_MY_PAGE) },
            showSnackBar = snackBarMessage
        )
        addAddVoteGraph(navController = navController)
    }
}

fun NavController.navigateToOpenSource() {
    val resourceProvider = context.resources
    context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
    OssLicensesMenuActivity.setActivityTitle(resourceProvider.getString(R.string.mypage_menu_open_source))
}