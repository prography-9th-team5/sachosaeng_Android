package com.example.sachosaeng.feature.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sachosaeng.core.util.LocalPushNotificationManager
import com.example.sachosaeng.core.util.NotificationPermissionManager
import com.example.sachosaeng.core.util.PushNotificationConstants
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_White
import kotlinx.coroutines.launch

@Composable
fun DebugTestMenu(
    modifier: Modifier = Modifier,
    notificationPermissionManager: NotificationPermissionManager,
    testPushByUser: (String, String) -> Unit = { _, _ -> },
    testPushByToken: (String, String) -> Unit = { _, _ -> }
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    if (context.applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE != 0) {
        MenuTitle(title = "푸시 알림 테스트 (디버그)")

        val isPermissionGranted = notificationPermissionManager.isNotificationPermissionGranted()
        val isNotificationDisabled = notificationPermissionManager.areNotificationsDisabled()

        if (!isPermissionGranted || isNotificationDisabled) {
            MyPageMenuCard(
                menuName = if (isNotificationDisabled) "알림 설정으로 이동" else "알림 권한 요청",
                onClick = {
                    context.startActivity(notificationPermissionManager.getNotificationSettingsIntent())
                }
            )
        }

        Column(
            modifier = modifier.padding(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            MyPageMenuCard(
                menuName = "오전 푸시 테스트",
                onClick = {
                    val pushManager = LocalPushNotificationManager(context)
                    scope.launch {
                        pushManager.sendImagePushNotification(
                            title = "테스트 - 오늘의 투표가 시작되었어요!",
                            message = "새로운 하루의 첫 번째 투표에 참여해보세요. 여러분의 의견이 중요합니다!",
                            imageUrl = "https://picsum.photos/400/300?random=1",
                            pushType = PushNotificationConstants.PushType.MORNING
                        )
                    }
                }
            )

            MyPageMenuCard(
                menuName = "오후 푸시 테스트",
                onClick = {
                    val pushManager = LocalPushNotificationManager(context)
                    scope.launch {
                        pushManager.sendImagePushNotification(
                            title = "테스트 - 점심시간 투표 참여하세요!",
                            message = "아직 투표에 참여하지 않으셨네요. 지금 바로 참여해보세요!",
                            imageUrl = "https://picsum.photos/400/300?random=2",
                            pushType = PushNotificationConstants.PushType.AFTERNOON
                        )
                    }
                }
            )

            MyPageMenuCard(
                menuName = "저녁 푸시 테스트",
                onClick = {
                    val pushManager = LocalPushNotificationManager(context)
                    scope.launch {
                        pushManager.sendImagePushNotification(
                            title = "테스트 - 오늘의 마지막 투표 기회!",
                            message = "하루가 끝나가기 전에 마지막 투표에 참여해보세요. 내일은 또 다른 주제가 기다려요!",
                            imageUrl = "https://picsum.photos/400/300?random=3",
                            pushType = PushNotificationConstants.PushType.EVENING
                        )
                    }
                }
            )

            MyPageMenuCard(
                menuName = "기본 푸시 테스트",
                onClick = {
                    val pushManager = LocalPushNotificationManager(context)
                    scope.launch {
                        pushManager.sendBasicPushNotification(
                            title = "기본 푸시 테스트",
                            message = "이미지 없이 발송되는 기본 푸시 알림입니다.",
                            pushType = PushNotificationConstants.PushType.TEST
                        )
                    }
                }
            )

            MyPageMenuCard(
                menuName = "서버 푸시 테스트 (유저)",
                onClick = {
                    scope.launch {
                        testPushByUser("서버 푸시 테스트(유저)", "서버를 통해 발송되는 테스트 푸시 알림입니다.")
                    }
                }
            )
            MyPageMenuCard(
                menuName = "서버 푸시 테스트 (토큰)",
                onClick = {
                    scope.launch {
                        testPushByToken("서버 푸시 테스트(토큰)", "토큰을 통해 발송되는 테스트 푸시 알림입니다.")
                    }
                }
            )
        }
    }
}

@Composable
fun MenuTitle(title: String) {
    Text(
        modifier = Modifier.padding(bottom = 14.dp),
        text = title,
        fontSize = 18.sp,
        color = Gs_Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MyPageMenuCard(menuName: String, onClick: () -> Unit = {}) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Gs_White
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = menuName,
                fontSize = 16.sp,
                color = Gs_Black
            )
        }
    }
}
