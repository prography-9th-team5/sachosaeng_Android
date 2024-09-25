package com.example.sachosaeng.main

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.component.bottomappbar.BottomAppbarItem
import com.example.sachosaeng.core.ui.component.bottomappbar.SachoSaengBottomAppBar
import com.example.sachosaeng.core.ui.component.snackbar.SachoSaengSnackbar
import com.example.sachosaeng.feature.bookmark.navigation.ROUTE_BOOKMARK
import com.example.sachosaeng.navigation.NavGraph
import com.example.sachosaeng.navigation.ROUTE_MAIN
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AppScreen(
    intent: Intent? = null,
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = hiltViewModel()
) {
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val isBottomBarNeeded =
        currentBackStackEntry?.destination?.route == ROUTE_MAIN || currentBackStackEntry?.destination?.route == ROUTE_BOOKMARK

    viewModel.collectSideEffect {
        when (it) {
            is AppSideEffect.NavigateToDeepLink -> intent?.data?.let {
                navController.handleDeepLink(
                    intent
                )
            }

            else -> {}
        }
    }

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                NavGraph(navController = navController, snackBarMessage = {
                    snackbarMessage = it
                })
                snackbarMessage?.let {
                    SachoSaengSnackbar(
                        Modifier.padding(bottom = 80.dp),
                        message = it, onDismiss = { snackbarMessage = null }
                    )
                }
            }
        },
        bottomBar = {
            if (isBottomBarNeeded) SachoSaengBottomAppBar(
                items = {
                    listOf(
                        BottomAppbarItem(ROUTE_MAIN, R.drawable.ic_home),
                        BottomAppbarItem(ROUTE_BOOKMARK, R.drawable.ic_bookmark)
                    )
                },
                navController = navController
            )
        }
    )
}