package com.example.sachosaeng.main

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sachosaeng.navigation.NavGraph
import com.example.sachosaeng.ui.theme.SachosaengTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(ContentValues.TAG, "Main onCreate")
        super.onCreate(savedInstanceState)
        setContent {
            SachosaengTheme {
                Text(text = "Hello, main!")
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}