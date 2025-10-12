package com.example.sachosaeng.core.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PushNotificationReceiver : BroadcastReceiver() {
    
    override fun onReceive(context: Context, intent: Intent) {
        val pushType = intent.getStringExtra(PushNotificationConstants.IntentExtra.PUSH_TYPE) ?: return
        val title = intent.getStringExtra(PushNotificationConstants.IntentExtra.TITLE) ?: return
        val message = intent.getStringExtra(PushNotificationConstants.IntentExtra.MESSAGE) ?: return
        val imageUrl = intent.getStringExtra(PushNotificationConstants.IntentExtra.IMAGE_URL)
        
        CoroutineScope(Dispatchers.IO).launch {
            val localPushManager = LocalPushNotificationManager(context)
            imageUrl?.let {
                localPushManager.sendImagePushNotification(
                    title = title,
                    message = message,
                    imageUrl = imageUrl,
                    pushType = pushType
                )
            } ?: run {
                localPushManager.sendBasicPushNotification(
                    title = title,
                    message = message,
                    pushType = pushType
                )
            }
        }
    }
}
