package com.example.sachosaeng.feature.addvote.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sachosaeng.feature.addvote.AddVoteScreen

const val ROUTE_ADD_VOTE = "addVote"
const val GRAPH_ADD_VOTE = "addVote_graph"
fun NavController.navigateToAddVote() {
    navigate(ROUTE_ADD_VOTE)
}

fun NavGraphBuilder.addAddVoteGraph(
    showSnackBar: (String) -> Unit,
    navController: NavHostController,
    navigateToSuggestedVoteHistory: () -> Unit
) {
    navigation(
        startDestination = ROUTE_ADD_VOTE,
        route = GRAPH_ADD_VOTE
    ) {
        composable(
            route = ROUTE_ADD_VOTE
        ) {
            AddVoteScreen(
                navigateToSuggestedVoteHistory = { navigateToSuggestedVoteHistory() },
                navigateToBackStack = { navController.popBackStack() },
                showSnackBar = showSnackBar
            )
        }
    }
}
