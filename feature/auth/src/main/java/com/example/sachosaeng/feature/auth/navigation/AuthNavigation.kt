package com.sachosaeng.app.feature.auth.navigation

import android.content.Intent
import androidx.navigation.NavController
import com.sachosaeng.app.feature.auth.AuthActivitiy

fun NavController.navigationToAuth() {
    val intent = Intent(context, AuthActivitiy::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    context.startActivity(Intent(intent))
}