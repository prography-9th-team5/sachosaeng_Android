package com.example.sachosaeng

import android.util.Log
import com.example.sachosaeng.core.usecase.push.SaveFcmTokenUseCase
import com.example.sachosaeng.core.util.LocalPushNotificationManager
import com.example.sachosaeng.core.util.PushNotificationConstants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SachoSaengFcmService : FirebaseMessagingService() {
    @Inject
    lateinit var saveFcmTokenUseCase: SaveFcmTokenUseCase
    
    @Inject
    lateinit var localPushNotificationManager: LocalPushNotificationManager
    
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        serviceScope.launch {
            try {
                saveFcmTokenUseCase(token)
            } catch (e: Exception) {
                Log.e(FCM_LOG_TAG, e.toString())
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title 
            ?: remoteMessage.data[TITLE]
            ?: TITLE

        val message = remoteMessage.notification?.body 
            ?: remoteMessage.data[MESSAGE]
            ?: MESSAGE
        
        if (message.isBlank()) {
            return
        }
        
        val pushType = remoteMessage.data[PUSH_TYPE]
            ?: PushNotificationConstants.PushType.SYSTEM
        
        val imageUrl = remoteMessage.data[IMAGE_URL]

        serviceScope.launch {
            try {
                if (!imageUrl.isNullOrEmpty()) {
                    localPushNotificationManager.sendImagePushNotification(
                        title = title,
                        message = message,
                        imageUrl = imageUrl,
                        pushType = pushType
                    )
                } else {
                    localPushNotificationManager.sendBasicPushNotification(
                        title = title,
                        message = message,
                        pushType = pushType
                    )
                }
            } catch (e: Exception) {
                Log.e(FCM_LOG_TAG, e.toString())
            }
        }
    }

    companion object {
        private const val IMAGE_URL = "image_url"
        private const val PUSH_TYPE = "push_type"
        private const val MESSAGE = "message"
        private const val TITLE = "title"
        const val FCM_LOG_TAG = "SachoSaengFcmService"
    }
}