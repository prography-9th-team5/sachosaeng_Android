package com.example.sachosaeng.feature.bookmark.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sachosaeng.feature.bookmark.BookmarkScreen

const val ROUTE_BOOKMARK = "bookmark"
const val GRAPH_BOOKMARK = "bookmark_graph"

fun NavGraphBuilder.addBookmarkGraph(
    navigateToVote: (Int) -> Unit,
    navigateToMyPage: () -> Unit
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
                moveToMyPage = navigateToMyPage
            )
        }
    }
}