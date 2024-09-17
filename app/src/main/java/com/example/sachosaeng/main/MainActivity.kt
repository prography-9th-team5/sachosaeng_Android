package com.example.sachosaeng.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.component.bottomappbar.BottomAppbarItem
import com.example.sachosaeng.core.ui.component.bottomappbar.SachoSaengBottomAppBar
import com.example.sachosaeng.feature.bookmark.navigation.ROUTE_BOOKMARK
import com.example.sachosaeng.navigation.ROUTE_MAIN
import com.example.sachosaeng.navigation.Screen
import com.example.sachosaeng.navigation.addNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val isBottomBarNeeded =
                currentBackStackEntry?.destination?.route == ROUTE_MAIN || currentBackStackEntry?.destination?.route == ROUTE_BOOKMARK
            Scaffold(
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        addNavGraph(navController = navController)
                    }
                },
                bottomBar = {
                    if (isBottomBarNeeded) SachoSaengBottomAppBar(
                        items = {
                            listOf(
                                BottomAppbarItem(Screen.HOME.route, R.drawable.ic_home),
                                BottomAppbarItem(ROUTE_BOOKMARK, R.drawable.ic_bookmark)
                            )
                        },
                        navController = navController
                    )
                }
            )
        }
    }
}