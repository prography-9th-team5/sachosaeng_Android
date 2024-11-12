package com.sachosaeng.app.feature.bookmark.navigation

import android.graphics.drawable.Drawable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sachosaeng.app.feature.bookmark.BookmarkScreen

const val ROUTE_BOOKMARK = "bookmark"
const val GRAPH_BOOKMARK = "bookmark_graph"

fun NavGraphBuilder.addBookmarkGraph(
    navigateToVote: (Int) -> Unit,
    navigateToArticle: (Int) -> Unit,
    navigateToMyPage: () -> Unit,
    showSnackBar: (String, Drawable) -> Unit = { _, _ -> }
) {
    navigation(
        startDestination = ROUTE_BOOKMARK,
        route = GRAPH_BOOKMARK
    ) {
        composable(
            route = ROUTE_BOOKMARK,
        ) {
            BookmarkScreen(
                moveToVote = navigateToVote,
                moveToMyPage = navigateToMyPage,
                moveToArticle = navigateToArticle,
                showSnackBar = showSnackBar
            )
        }
    }
}