package com.example.sachosaeng.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PushTestManager @Inject constructor(
    private val pushScheduler: PushScheduler,
    private val localPushNotificationManager: LocalPushNotificationManager,
    private val notificationPermissionManager: NotificationPermissionManager
) {
    
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun sendTestMorningPush() {
        coroutineScope.launch {
            val title = "테스트 - 오늘의 투표가 시작되었어요!"
            val message = "새로운 하루의 첫 번째 투표에 참여해보세요. 여러분의 의견이 중요합니다!"
            val imageUrl = "https://picsum.photos/400/300?random=1"
            
            pushScheduler.sendImmediateNotification(
                PushNotificationConstants.PushType.MORNING,
                title,
                message,
                imageUrl
            )
        }
    }

    fun sendTestAfternoonPush() {
        coroutineScope.launch {
            val title = "점심시간 투표 참여하세요!"
            val message = "아직 투표에 참여하지 않으셨네요. 지금 바로 참여해보세요!"
            val imageUrl = "https://picsum.photos/400/300?random=2"
            
            pushScheduler.sendImmediateNotification(
                PushNotificationConstants.PushType.AFTERNOON,
                title,
                message,
                imageUrl
            )
        }
    }

    fun sendTestEveningPush() {
        coroutineScope.launch {
            val title = "오늘의 마지막 투표 기회!"
            val message = "하루가 끝나가기 전에 마지막 투표에 참여해보세요. 내일은 또 다른 주제가 기다려요!"
            val imageUrl = "https://picsum.photos/400/300?random=3"
            
            pushScheduler.sendImmediateNotification(
                PushNotificationConstants.PushType.EVENING,
                title,
                message,
                imageUrl
            )
        }
    }

    fun sendTestBasicPush() {
        coroutineScope.launch {
            val title = "기본 푸시 테스트"
            val message = "이미지 없이 발송되는 기본 푸시 알림입니다."
            
            localPushNotificationManager.sendBasicPushNotification(
                title = title,
                message = message,
                pushType = "test"
            )
        }
    }

    fun getNotificationPermissionStatus(): NotificationPermissionStatus {
        return NotificationPermissionStatus(
            isGranted = notificationPermissionManager.isNotificationPermissionGranted(),
            shouldRequest = notificationPermissionManager.shouldRequestNotificationPermission(),
            isDisabled = notificationPermissionManager.areNotificationsDisabled()
        )
    }

    fun startPushScheduling() {
        pushScheduler.scheduleVoteNotifications()
    }

    fun stopPushScheduling() {
        pushScheduler.cancelAllVoteNotifications()
    }
}

data class NotificationPermissionStatus(
    val isGranted: Boolean,
    val shouldRequest: Boolean,
    val isDisabled: Boolean
)
