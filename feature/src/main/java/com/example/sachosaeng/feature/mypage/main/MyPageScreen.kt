package com.example.sachosaeng.feature.mypage.main

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_G6
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.feature.R
import com.example.sachosaeng.feature.util.component.DetailScreenTopbar
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun MyPageScreen(
    navigateToWithDraw: () -> Unit = {},
    navigateToBackStack: () -> Unit = {},
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getUserInfo()
    }
    if (state.withdrawDialogState) {
        WithdrawDialog(
            onWithdraw = {
                viewModel.hideWithdrawDialog()
                navigateToWithDraw()
            },
            onCancel = { viewModel.hideWithdrawDialog() }
        )
    }
    MyPageScreen(
        state,
        onWithdraw = { viewModel.showWithdrawDialog() },
        navigateToBackStack = { navigateToBackStack() })
}

@Composable
internal fun MyPageScreen(
    myPageUiState: MyPageUiState,
    onWithdraw: () -> Unit = {},
    navigateToBackStack: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gs_G2)
            .padding(horizontal = 20.dp),
    ) {
        item {
            DetailScreenTopbar(
                pageLabel = stringResource(id = R.string.mypage_top_bar_label),
                navigateToBackStack = { navigateToBackStack() })
            UserInfoCard(levelText = myPageUiState.levelText, userName = myPageUiState.userName)
            MyPageMenuList(
                modifier = Modifier.padding(vertical = 28.dp),
                menuCard = listOf(
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = R.string.mypage_menu_change_nickname)
                        )
                    },
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = R.string.mypage_menu_change_category)
                        )
                    },
                    {
                        MyPageMenuCard(
                            menuName = stringResource(id = R.string.mypage_menu_suggest_vote)
                        )
                    }
                )
            )
            MenuTitle(title = stringResource(id = R.string.mypage_menu_setting))
            MyPageMenuList(
                menuCard = listOf(
                    { VersionInfoCard(versionInfo = myPageUiState.versionInfo) },
                    { MyPageMenuCard(menuName = stringResource(id = R.string.mypage_menu_open_source)) },
                    { MyPageMenuCard(menuName = stringResource(id = R.string.mypage_menu_privacy_policy)) },
                    { MyPageMenuCard(menuName = stringResource(id = R.string.mypage_menu_terms_of_service)) },
                    { MyPageMenuCard(menuName = stringResource(id = R.string.mypage_menu_faq)) }
                )
            )
            WithdrawButton(onClick = { onWithdraw() })
        }
    }
}

@Composable
fun UserInfoCard(levelText: String, userName: String) {
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = Gs_G6),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(end = 20.dp),
                contentDescription = "",
                painter = painterResource(id = R.drawable.ic_level_newcomer),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = levelText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    color = Gs_White
                )
                Text(
                    text = userName,
                    color = Gs_White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700
                )
            }
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
fun MyPageMenuCard(menuName: String) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = Gs_White
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(text = menuName)
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
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
                text = stringResource(id = R.string.mypage_menu_version_info)
            )
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(id = R.string.mypage_latest_version),
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
fun WithdrawButton(onClick: () -> Unit = {}) {
    Text(
        color = Gs_G5,
        text = stringResource(id = R.string.mypage_withdraw_label),
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(vertical = 20.dp)
    )
}