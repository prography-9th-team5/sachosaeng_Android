package com.example.sachosaeng.feature.vote.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.sachosaeng.feature.vote.VoteScreen

const val ROUTE_VOTE = "vote"
const val GRAPH_VOTE = "vote_graph"
const val VOTE_DETAIL_ID = "voteDetail"
internal const val ROUTE_VOTE_DETAIL = "$ROUTE_VOTE/$VOTE_DETAIL_ID"

fun NavController.navigateToVoteDetail(voteId: Int?) {
    navigate("$ROUTE_VOTE_DETAIL?$VOTE_DETAIL_ID=$voteId")
}

fun NavGraphBuilder.addVoteGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = ROUTE_VOTE_DETAIL,
        route = GRAPH_VOTE
    ) {
        composable(
            route = "$ROUTE_VOTE_DETAIL?$VOTE_DETAIL_ID={$VOTE_DETAIL_ID}",
            arguments = listOf(
                navArgument(VOTE_DETAIL_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
            )
        ) { backStackEntry ->
            VoteScreen(navigateToBackStack = { /* TODO */ })
        }
    }
}