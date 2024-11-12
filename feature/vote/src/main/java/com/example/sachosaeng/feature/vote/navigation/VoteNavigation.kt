package com.sachosaeng.app.feature.vote.navigation

import android.graphics.drawable.Drawable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sachosaeng.app.feature.vote.VoteDetailSideEffect
import com.sachosaeng.app.feature.vote.VoteScreen

const val ROUTE_VOTE = "vote"
const val GRAPH_VOTE = "vote_graph"
const val VOTE_DETAIL_ID = "voteDetail"
const val VOTE_IS_DAILY = "isDailyVote"
internal const val ROUTE_VOTE_DETAIL = "$ROUTE_VOTE/$VOTE_DETAIL_ID"

fun NavController.navigateToVoteDetail(voteId: Int?, isDailyVote: Boolean) {
    navigate("$ROUTE_VOTE_DETAIL?$VOTE_DETAIL_ID=$voteId?$VOTE_IS_DAILY=$isDailyVote")
}

fun NavGraphBuilder.addVoteGraph(
    showSnackBar: (String, Drawable) -> Unit,
    navController: NavHostController,
    navigateToArticleDetail: (Int, Int) -> Unit
) {
    navigation(
        startDestination = ROUTE_VOTE_DETAIL,
        route = GRAPH_VOTE
    ) {
        composable(
            route = "$ROUTE_VOTE_DETAIL?$VOTE_DETAIL_ID={$VOTE_DETAIL_ID}?${VOTE_IS_DAILY}={$VOTE_IS_DAILY}",
            arguments = listOf(
                navArgument(VOTE_DETAIL_ID) {
                    type = NavType.IntType
                    nullable = false
                    defaultValue = 1
                },
                navArgument(VOTE_IS_DAILY) {
                    type = NavType.BoolType
                    nullable = false
                    defaultValue = false
                }
            )
        ) {
            VoteScreen(
                showSnackBar = showSnackBar,
                navigateToBackStack = { navController.popBackStack() },
                navigateToArticleDetail = { articleId, categoryId -> navigateToArticleDetail(articleId , categoryId) }
            )
        }
    }
}