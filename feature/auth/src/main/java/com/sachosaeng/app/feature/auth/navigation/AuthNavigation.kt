package com.sachosaeng.app.feature.auth.navigation

import android.content.Intent
import androidx.navigation.NavController
import com.sachosaeng.app.feature.auth.AuthActivitiy

fun NavController.navigationToAuth() {
    context.startActivity(Intent(context, AuthActivitiy::class.java))
}