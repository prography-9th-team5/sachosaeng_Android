package com.example.sachosaeng.feature.auth.navigation

import android.content.Intent
import androidx.navigation.NavController
import com.example.sachosaeng.feature.auth.AuthActivitiy

fun NavController.navigationToAuth() {
    context.startActivity(Intent(context, AuthActivitiy::class.java))
}