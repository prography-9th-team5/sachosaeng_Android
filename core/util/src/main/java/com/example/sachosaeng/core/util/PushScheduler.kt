package com.example.sachosaeng.core.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.sachosaeng.app.core.util.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PushScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val localPushNotificationManager: LocalPushNotificationManager,
    private val notificationPermissionManager: NotificationPermissionManager
) {
    
    companion object {
        private const val PENDING_INTENT_FLAGS = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    }
    
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    
    data class ScheduleData(
        val id: String,
        val title: String,
        val message: String,
        val imageUrl: String? = null,
        val hour: Int,
        val minute: Int,
        val requestCode: Int
    )
    
    private val voteSchedules by lazy {
        listOf(
            ScheduleData(
                id = PushNotificationConstants.PushType.MORNING,
                title = context.getString(R.string.push_morning_title),
                message = context.getString(R.string.push_morning_message),
                imageUrl = context.getString(R.string.push_morning_image_url),
                hour = 8,
                minute = 0,
                requestCode = 2001
            ),
            ScheduleData(
                id = PushNotificationConstants.PushType.AFTERNOON,
                title = context.getString(R.string.push_afternoon_title),
                message = context.getString(R.string.push_afternoon_message),
                imageUrl = context.getString(R.string.push_afternoon_image_url),
                hour = 12,
                minute = 0,
                requestCode = 2002
            ),
            ScheduleData(
                id = PushNotificationConstants.PushType.EVENING,
                title = context.getString(R.string.push_evening_title),
                message = context.getString(R.string.push_evening_message),
                imageUrl = context.getString(R.string.push_evening_image_url),
                hour = 19,
                minute = 0,
                requestCode = 2003
            )
        )
    }

    fun scheduleVoteNotifications() {
        voteSchedules.forEach { scheduleData ->
            scheduleNotification(scheduleData)
        }
    }

    fun scheduleNotification(scheduleData: ScheduleData) {
        val intent = createNotificationIntent(scheduleData)
        val pendingIntent = createPendingIntent(intent, scheduleData.requestCode)
        val calendar = createScheduleCalendar(scheduleData.hour, scheduleData.minute)
        
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun createNotificationIntent(scheduleData: ScheduleData): Intent {
        return Intent(context, PushNotificationReceiver::class.java).apply {
            putExtra(PushNotificationConstants.IntentExtra.PUSH_TYPE, scheduleData.id)
            putExtra(PushNotificationConstants.IntentExtra.TITLE, scheduleData.title)
            putExtra(PushNotificationConstants.IntentExtra.MESSAGE, scheduleData.message)
            scheduleData.imageUrl?.let { 
                putExtra(PushNotificationConstants.IntentExtra.IMAGE_URL, it) 
            }
        }
    }

    private fun createPendingIntent(intent: Intent, requestCode: Int): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PENDING_INTENT_FLAGS
        )
    }

    private fun createScheduleCalendar(hour: Int, minute: Int): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }
    }

    fun cancelNotification(notificationId: String) {
        val scheduleData = voteSchedules.find { it.id == notificationId } ?: return
        val intent = Intent(context, PushNotificationReceiver::class.java)
        val pendingIntent = createPendingIntent(intent, scheduleData.requestCode)
        alarmManager.cancel(pendingIntent)
    }

    fun cancelAllVoteNotifications() {
        voteSchedules.forEach { scheduleData ->
            val intent = Intent(context, PushNotificationReceiver::class.java)
            val pendingIntent = createPendingIntent(intent, scheduleData.requestCode)
            alarmManager.cancel(pendingIntent)
        }
    }

    fun sendNotification(notificationId: String, title: String, message: String, imageUrl: String? = null) {
        coroutineScope.launch {
            if (!notificationPermissionManager.isNotificationPermissionGranted()) {
                return@launch
            }
            sendPushNotification(notificationId, title, message, imageUrl)
        }
    }
    
    fun sendImmediateNotification(notificationId: String, title: String, message: String, imageUrl: String? = null) {
        coroutineScope.launch {
            sendPushNotification(notificationId, title, message, imageUrl)
        }
    }

    private suspend fun sendPushNotification(notificationId: String, title: String, message: String, imageUrl: String?) {
        if (imageUrl != null) {
            localPushNotificationManager.sendImagePushNotification(
                title = title,
                message = message,
                imageUrl = imageUrl,
                pushType = notificationId
            )
        } else {
            localPushNotificationManager.sendBasicPushNotification(
                title = title,
                message = message,
                pushType = notificationId
            )
        }
    }
}
