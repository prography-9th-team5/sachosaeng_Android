package com.example.sachosaeng.core.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sachosaeng.app.core.util.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalPushNotificationManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        const val CHANNEL_ID = PushNotificationConstants.CHANNEL_ID
        const val PUSH_TYPE_MORNING = PushNotificationConstants.PushType.MORNING
        const val PUSH_TYPE_AFTERNOON = PushNotificationConstants.PushType.AFTERNOON
        const val PUSH_TYPE_EVENING = PushNotificationConstants.PushType.EVENING
    }
    
    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.notification_channel_description)
                enableLights(true)
                enableVibration(true)
            }
            
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun sendImagePushNotification(
        title: String,
        message: String,
        imageUrl: String? = null,
        pushType: String = PUSH_TYPE_MORNING
    ) {
        val notificationId = getNotificationId(pushType)
        val bitmap = imageUrl?.let { loadImageFromUrl(it) }
        
        val intent = createPushNotificationIntent(pushType)
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(message)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .apply {
                bitmap?.let { bmp ->
                    setStyle(
                        NotificationCompat.BigPictureStyle()
                            .bigPicture(bmp)
                            .bigLargeIcon(null as Bitmap?)
                    )
                    setLargeIcon(bmp)
                }
            }
            .build()
        
        try {
            withContext(Dispatchers.Main) {
                NotificationManagerCompat.from(context).notify(notificationId, notification)
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("MissingPermission")
    fun sendBasicPushNotification(
        title: String,
        message: String,
        pushType: String = PUSH_TYPE_MORNING
    ) {
        val notificationId = getNotificationId(pushType)
        
        val intent = createPushNotificationIntent(pushType)
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(message)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        
        try {
            NotificationManagerCompat.from(context).notify(notificationId, notification)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private suspend fun loadImageFromUrl(imageUrl: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            
            val inputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun getNotificationId(pushType: String): Int {
        return when (pushType) {
            PushNotificationConstants.PushType.MORNING -> PushNotificationConstants.NotificationId.MORNING
            PushNotificationConstants.PushType.AFTERNOON -> PushNotificationConstants.NotificationId.AFTERNOON
            PushNotificationConstants.PushType.EVENING -> PushNotificationConstants.NotificationId.EVENING
            PushNotificationConstants.PushType.WEEKLY -> PushNotificationConstants.NotificationId.WEEKLY
            PushNotificationConstants.PushType.EVENT -> PushNotificationConstants.NotificationId.EVENT
            PushNotificationConstants.PushType.SYSTEM -> PushNotificationConstants.NotificationId.SYSTEM
            else -> PushNotificationConstants.NotificationId.MORNING
        }
    }

    fun cancelNotification(pushType: String) {
        val notificationId = getNotificationId(pushType)
        NotificationManagerCompat.from(context).cancel(notificationId)
    }

    fun cancelAllNotifications() {
        NotificationManagerCompat.from(context).cancelAll()
    }

    private fun createPushNotificationIntent(pushType: String): Intent {
        return Intent().apply {
            setClassName(context, PushNotificationConstants.Activity.MAIN_ACTIVITY)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(PushNotificationConstants.IntentExtra.PUSH_TYPE, pushType)
            putExtra(PushNotificationConstants.IntentExtra.NAVIGATE_TO, "vote")
        }
    }
}
