package com.example.sachosaeng.core.util

import android.os.Bundle
import androidx.core.os.bundleOf
import com.google.firebase.ktx.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

object FirebaseUtil {
    fun setUser(identifier: String) {
        Firebase.analytics.setUserId(identifier)
    }

    fun setScreenView(screenName: String, bundle: Bundle = bundleOf()) {
        Firebase.analytics.logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW,
            bundle.apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            }
        )
    }

    suspend fun getCurrentFcmToken(): String {
        return try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            android.util.Log.e(FIREBASE_LOG_TAG, e.toString())
            ""
        }
    }
    private const val FIREBASE_LOG_TAG = "FirebaseUtil"
    const val PLATFORM = "android"
    const val SCREEN_NAME_HOME = "home"
    const val SCREEN_NAME_VOTE = "vote"
    const val SCREEN_NAME_DAILY_VOTE = "daily_vote"
    const val SCREEN_NAME_ARTICLE = "article"
    const val SCREEN_NAME_ADD_VOTE = "add_vote"
}