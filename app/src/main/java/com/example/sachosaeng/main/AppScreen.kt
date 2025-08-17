package com.sachosaeng.app.main

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sachosaeng.core.ui.component.bottomappbar.BottomAppbarItem
import com.example.sachosaeng.core.ui.component.tooltip.SachosaengTextTooltip
import com.example.sachosaeng.core.ui.component.tooltip.SachosaengTooltipWrapper
import com.example.sachosaeng.feature.home.navigation.navigateToMain
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.component.bottomappbar.SachoSaengBottomAppBar
import com.sachosaeng.app.core.ui.component.snackbar.SachoSaengSnackbar
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.util.constant.NavigationConstant.Main.ROUTE_MAIN
import com.sachosaeng.app.feature.auth.navigation.navigationToAuth
import com.sachosaeng.app.feature.bookmark.navigation.ROUTE_BOOKMARK
import com.sachosaeng.app.feature.mypage.navigation.ROUTE_MY_PAGE
import com.sachosaeng.app.navigation.NavGraph
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    intent: Intent? = null,
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = hiltViewModel()
) {
    var snackbarStatus by remember { mutableStateOf<Pair<String?, Int?>?>(Pair("", null)) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val isBottomBarNeeded =
        currentBackStackEntry?.destination?.route == ROUTE_MAIN || currentBackStackEntry?.destination?.route == ROUTE_BOOKMARK || currentBackStackEntry?.destination?.route == ROUTE_MY_PAGE
    val state by viewModel.collectAsState()
    val tooltipState = rememberTooltipState(isPersistent = true, initialIsVisible = false)

    val popupPositionProvider = remember {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize
            ): IntOffset {
                val x = anchorBounds.right - (popupContentSize.width * 1.35).toInt()
                val y =  anchorBounds.top - popupContentSize.height + 15
                return IntOffset(x, y)
            }
        }
    }

    LaunchedEffect(state.tooltipState.isVisible) {
       if(state.tooltipState.isVisible) tooltipState.show()
    }


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
                    },
                    showLevelUpTooltip = {
                        viewModel.showLevelUpTooltip()
                    }
                )
                snackbarStatus?.first?.let { message ->
                    if (message.isNotEmpty()) SachoSaengSnackbar(
                        iconResId = snackbarStatus?.second,
                        message = message,
                        onDismiss = { snackbarStatus = null }
                    )
                }
            }
        },
        bottomBar = {
            if (isBottomBarNeeded)
                SachosaengTooltipWrapper(
                    message = state.tooltipState.tooltipMessage,
                    state = tooltipState,
                    positionProvider = popupPositionProvider
                ) {
                    SachoSaengBottomAppBar(
                        items = {
                            listOf(
                                BottomAppbarItem(
                                    ROUTE_MAIN,
                                    onIcon = R.drawable.ic_home_on,
                                    offIcon = R.drawable.ic_home,
                                    label = R.string.home
                                ),
                                BottomAppbarItem(
                                    ROUTE_BOOKMARK,
                                    onIcon = R.drawable.ic_bookmark_on,
                                    offIcon = R.drawable.ic_bookmark,
                                    label = R.string.bookmark
                                ),
                                BottomAppbarItem(
                                    ROUTE_MY_PAGE,
                                    onIcon = R.drawable.ic_gnb_profile_on,
                                    offIcon = R.drawable.ic_gnb_profile_off,
                                    label = R.string.my_info
                                ),
                            )
                        },
                        navController = navController
                    )
                }
        }
    )
}