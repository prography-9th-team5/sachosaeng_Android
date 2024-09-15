package com.example.sachosaeng.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.component.bottomappbar.BottomAppbarItem
import com.example.sachosaeng.core.ui.component.bottomappbar.SachoSaengBottomAppBar
import com.example.sachosaeng.feature.bookmark.navigation.ROUTE_BOOKMARK
import com.example.sachosaeng.navigation.Screen
import com.example.sachosaeng.navigation.addNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lateinit var navController: NavHostController
            navController = rememberNavController()

            //todo: 더 다듬을 수 있는지 생각해보기
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
                    SachoSaengBottomAppBar(
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