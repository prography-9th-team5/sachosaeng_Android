package com.sachosaeng.app.feature.mypage.main

import android.app.Activity
import android.content.ContextWrapper
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.extension.captureComposableAsBitmap
import com.example.sachosaeng.core.util.LocalPushNotificationManager
import com.example.sachosaeng.core.util.NotificationPermissionManager
import com.example.sachosaeng.feature.mypage.component.DebugTestMenu
import com.example.sachosaeng.feature.mypage.component.DownloadCompleteDialog
import com.example.sachosaeng.feature.mypage.component.LevelUpDialog
import com.example.sachosaeng.feature.mypage.notification.NotificationScreen
import com.example.sachosaeng.feature.mypage.notification.NotificationType
import com.sachosaeng.app.core.model.User
import com.sachosaeng.app.core.model.UserScore
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G2
import com.sachosaeng.app.core.ui.theme.Gs_G5
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.feature.mypage.R.drawable
import com.sachosaeng.app.feature.mypage.component.LogoutDialog
import com.sachosaeng.app.feature.mypage.component.UserInfoCard
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MyPageScreen(
    navigateToAlertPage: (NotificationType) -> Unit = { _ ->},
    navigateToModifyCategory: () -> Unit = {},
    navigateToUserInfoModify: () -> Unit = {},
    navigateToPrivacyPolicy: () -> Unit = {},
    navigateToTermsOfService: () -> Unit = {},
    navigateToFaq: () -> Unit = {},
    navigateToRequestToAdmin: () -> Unit = {},
    navigateToOpenSource: () -> Unit = {},
    navigateToSuggestVoteHistory: () -> Unit = {},
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getUserInfo()
    }

    viewModel.collectSideEffect {
        when (it) {
            is MyPageSideEffect.NavigateToAlertPage -> navigateToAlertPage(NotificationType.LEVEL_UP)
            else -> {}
        }
    }
    if (state.downloadCompleteDialogState) DownloadCompleteDialog(
        onClick = { viewModel.hideDownloadDialog() }
    )
    if (state.logoutDialogState) {
        LogoutDialog(
            onLogout = { viewModel.logout() },
            onCancel = { viewModel.hideLogoutDialog() }
        )
    }
    if (state.levelUpDialogState) {
        LevelUpDialog()
    }

    MyPageScreen(
        myPageUiState = state,
        onLogout = viewModel::showLogoutDialog,
        onModifyUserInfo = navigateToUserInfoModify,
        navigateToModifyCategory = navigateToModifyCategory,
        navigateToPrivacyPolicy = navigateToPrivacyPolicy,
        navigateToTermsOfService = navigateToTermsOfService,
        navigateToFaq = navigateToFaq,
        navigateToRequestToAdmin = navigateToRequestToAdmin,
        navigateToOpenSource = navigateToOpenSource,
        navigateToSuggestVoteHistory = navigateToSuggestVoteHistory,
        onDownloadImage = viewModel::downloadImage,
        onShowAlert = viewModel::onShowAlert
    )
}

@Composable
internal fun MyPageScreen(
    myPageUiState: MyPageUiState,
    onLogout: () -> Unit = {},
    navigateToModifyCategory: () -> Unit = {},
    onModifyUserInfo: () -> Unit = {},
    navigateToPrivacyPolicy: () -> Unit = {},
    navigateToTermsOfService: () -> Unit = {},
    navigateToFaq: () -> Unit = {},
    navigateToRequestToAdmin: () -> Unit = {},
    navigateToOpenSource: () -> Unit = {},
    navigateToSuggestVoteHistory: () -> Unit = {},
    onShowAlert: () -> Unit = {},
    onDownloadImage: (bitmap: Bitmap) -> Unit = {}
) {
    val context = LocalContext.current
    val activity = remember {
        generateSequence(context) { (it as? ContextWrapper)?.baseContext }
            .filterIsInstance<Activity>()
            .firstOrNull()
    }

    val scope = rememberCoroutineScope()
    
    val notificationPermissionManager = NotificationPermissionManager(context)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gs_G2)
            .padding(20.dp),
    ) {
        item {
            UserInfoCard(
                userInfo = myPageUiState.userInfo,
                userInfoModifyButtonClick = onModifyUserInfo,
                onDownloadImage = {
                    activity?.let {
                        scope.launch {
                            val bitmap = activity.captureComposableAsBitmap(
                                widthDp = 300.dp,
                                heightDp = 300.dp,
                                content = {
                                    UserInfoCard(
                                        userInfo = myPageUiState.userInfo,
                                        userInfoModifyButtonClick = onModifyUserInfo,
                                        onDownloadImage = {},
                                        onShowAlert = onShowAlert
                                    )
                                },
                            )
                            onDownloadImage(bitmap)
                        }
                    }
                },
                onShowAlert = onShowAlert
            )
        }
        item {
            MyPageMenuList(
                modifier = Modifier.padding(vertical = 28.dp),
                menuCard = listOf(
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = string.mypage_menu_change_category),
                            onClick = navigateToModifyCategory
                        )
                    },
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = string.mypage_menu_suggest_vote),
                            onClick = navigateToRequestToAdmin
                        )
                    },
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = string.mypage_menu_history_of_suggested_vote),
                            onClick = navigateToSuggestVoteHistory
                        )
                    }
                )
            )
        }
        item {
            DebugTestMenu(notificationPermissionManager = notificationPermissionManager)
        }
        item {
            MenuTitle(title = stringResource(id = string.mypage_menu_setting))
            MyPageMenuList(
                menuCard = listOf(
                    {
                        VersionInfoCard(
                            versionInfo = myPageUiState.versionInfo
                        )
                    },
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = string.mypage_menu_open_source),
                            onClick = navigateToOpenSource
                        )
                    },
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = string.mypage_menu_privacy_policy),
                            onClick = { navigateToPrivacyPolicy() })
                    },
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = string.mypage_menu_terms_of_service),
                            onClick = { navigateToTermsOfService() })
                    },
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = string.mypage_menu_faq),
                            onClick = navigateToFaq
                        )
                    }
                )
            )
        }
        item {
            LogoutButton(onClick = { onLogout() })
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
        fontWeight = FontWeight.W700,
    )
}

@Composable
fun MyPageMenuList(modifier: Modifier = Modifier, menuCard: List<@Composable () -> Unit>) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        menuCard.forEach { card ->
            card()
        }
    }
}

@Composable
fun MyPageMenuCard(menuName: String, onClick: () -> Unit = {}) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Gs_White
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .clickable { onClick() }
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(text = menuName)
            Image(
                painter = painterResource(id = drawable.ic_arrow_right),
                contentDescription = null
            )
        }
    }
}

@Composable
fun VersionInfoCard(versionInfo: String) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Gs_White
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = string.mypage_menu_version_info)
            )
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(id = string.mypage_latest_version),
                color = Gs_G5,
                fontSize = 15.sp
            )
            Text(
                modifier = Modifier
                    .background(color = Gs_G2, shape = CircleShape)
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                text = "v$versionInfo",
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun LogoutButton(onClick: () -> Unit = {}) {
    Text(
        color = Gs_G5,
        text = stringResource(id = string.mypage_logout_label),
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(vertical = 20.dp)
    )
}

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(
        myPageUiState = MyPageUiState(
            userInfo = User(
                name = "홍길동",
                userTypeName = "STUDENT",
                level = 1,
                voteScore = UserScore(count = 10, score = 100),
                registerVoteScore = UserScore(count = 5, score = 50),
                readArticleScore = UserScore(count = 20, score = 200),
                score = 350
            ),
            versionInfo = "1.0.0",
            logoutDialogState = true,
            downloadCompleteDialogState = true
        )
    )
}