package com.example.sachosaeng.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sachosaeng.core.ui.theme.SachosaengTheme
import com.example.sachosaeng.feature.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SachosaengTheme {
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}