package com.sachosaeng.app.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sachosaeng.app.feature.search.SearchScreen

private const val SEARCH = "search"
const val GRAPH_SEARCH = "searchGraph"

fun NavController.navigateToSearch() {
    navigate(SEARCH)
}

fun NavGraphBuilder.addSearchNavGraph(
    navController: NavHostController,
    navigateToVoteDetail: (Int, Boolean) -> Unit = { _, _ -> },
    snackBarMessage: (String) -> Unit = {}
) {
    navigation(
        route = GRAPH_SEARCH,
        startDestination = SEARCH
    ) {
        composable(
            route = SEARCH
        ) {
            SearchScreen(
                navigateToVoteCard = { voteId, isDailyVote ->
                    navigateToVoteDetail(voteId, isDailyVote)
                }
            )
        }
    }
}