package com.sachosaeng.app.main

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sachosaeng.feature.home.navigation.navigateToMain
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.bottomappbar.BottomAppbarItem
import com.sachosaeng.app.core.ui.component.bottomappbar.SachoSaengBottomAppBar
import com.sachosaeng.app.core.ui.component.snackbar.SachoSaengSnackbar
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.util.constant.NavigationConstant.Main.ROUTE_MAIN
import com.sachosaeng.app.feature.auth.navigation.navigationToAuth
import com.sachosaeng.app.feature.bookmark.navigation.ROUTE_BOOKMARK
import com.sachosaeng.app.navigation.NavGraph
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AppScreen(
    intent: Intent? = null,
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = hiltViewModel()
) {
    var snackbarStatus by remember { mutableStateOf<Pair<String?, Int?>?>(Pair("", null)) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val isBottomBarNeeded =
        currentBackStackEntry?.destination?.route == ROUTE_MAIN || currentBackStackEntry?.destination?.route == ROUTE_BOOKMARK

    BackHandler {
        viewModel.backPressed(currentBackStackEntry?.destination?.route)
    }

    LaunchedEffect(Unit) {
        if (intent?.data != null) {
            navController.handleDeepLink(
                intent
            )
        }
    }

    viewModel.collectSideEffect {
        when (it) {
            is AppSideEffect.NavigateToMainRoute -> navController.navigateToMain()
            is AppSideEffect.NavigateToAuthActivity -> navController.navigationToAuth()
            is AppSideEffect.ShowSnackBar -> snackbarStatus = Pair(it.message, it.drawableRes)
            else -> {}
        }
    }

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(Gs_G2)
            ) {
                NavGraph(
                    navController = navController,
                    snackBarMessage = { message, drawableRes ->
                        snackbarStatus = Pair(message, drawableRes)
                    }
                )
                snackbarStatus?.first?.let { message ->
                    if(message.isNotEmpty()) SachoSaengSnackbar(
                        iconResId = snackbarStatus?.second,
                        message = message,
                        onDismiss = { snackbarStatus = null }
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