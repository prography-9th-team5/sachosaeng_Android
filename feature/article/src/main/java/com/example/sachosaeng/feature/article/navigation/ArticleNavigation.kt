package com.sachosaeng.app.feature.article.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sachosaeng.app.feature.article.ArticleDetailScreen

const val ROUTE_ARTICLE = "article"
const val GRAPH_ARTICLE = "article_graph"
const val ARTICLE_DETAIL_ID = "articleDetail"
const val ARTICLE_CATEGORY_ID = "categoryId"
internal const val ROUTE_ARTICLE_DETAIL = "$ROUTE_ARTICLE/$ARTICLE_DETAIL_ID"

fun NavController.navigateToArticleDetail(articleId: Int?, categoryId: Int? = null) {
    navigate("$ROUTE_ARTICLE_DETAIL?$ARTICLE_DETAIL_ID=$articleId?$ARTICLE_CATEGORY_ID=$categoryId")
}

fun NavGraphBuilder.addArticleGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = ROUTE_ARTICLE_DETAIL,
        route = GRAPH_ARTICLE
    ) {
        composable(
            route = "$ROUTE_ARTICLE_DETAIL?$ARTICLE_DETAIL_ID={$ARTICLE_DETAIL_ID}?$ARTICLE_CATEGORY_ID={$ARTICLE_CATEGORY_ID}",
            arguments = listOf(
                navArgument(ARTICLE_DETAIL_ID) {
                    type = NavType.IntType
                    nullable = false
                    defaultValue = 1
                },
                navArgument(ARTICLE_CATEGORY_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            ArticleDetailScreen(
                navigateToBackStack = { navController.popBackStack() }
            )
        }
    }
}
