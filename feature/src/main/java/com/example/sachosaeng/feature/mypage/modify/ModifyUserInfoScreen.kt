package com.example.sachosaeng.feature.mypage.modify

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.component.snackbar.SachoSaengSnackbar
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.feature.R
import com.example.sachosaeng.feature.signup.selectusertype.UserType
import com.example.sachosaeng.feature.util.component.DetailScreenTopbar
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ModifyUserInfoScreen(
    navigateToBackStack: () -> Unit = {},
    navigateToWithdrawScreen: () -> Unit = {},
    viewModel: ModifyUserInfoViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    viewModel.collectSideEffect {
        when (it) {
            is ModifyUserInfoSideEffect.ShowSnackBar -> {
                snackbarMessage = it.message
            }
        }
    }

    ModifyUserInfoScreen(
        state = state,
        onNicknameChange = viewModel::onNickNameChange,
        onUserTypeSelect = viewModel::onUserTypeSelect,
        onWithdraw = { navigateToWithdrawScreen() },
        navigateToBackStack = navigateToBackStack,
        saveUserInfo = viewModel::saveUserInfo
    )


    snackbarMessage?.let {
        SachoSaengSnackbar(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_warning_black_small),
                    contentDescription = null
                )
            }, message = it, onDismiss = {
                snackbarMessage = null
                navigateToBackStack()
            })
    }
}

@Composable
internal fun ModifyUserInfoScreen(
    state: ModifiyUserInfoUiState,
    onNicknameChange: (String) -> Unit,
    onUserTypeSelect: (UserType) -> Unit,
    saveUserInfo: () -> Unit = {},
    onWithdraw: () -> Unit = {},
    navigateToBackStack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Gs_G2)
            .padding(horizontal = 20.dp)
    ) {
        DetailScreenTopbar(
            pageLabel = stringResource(id = R.string.mypage_modify_userinfo),
            navigateToBackStack = { navigateToBackStack() }
        )
        ProfileImage(userType = state.userType)
        NickNameField(state.userName, onNicknameChange = onNicknameChange)
        Spacer(modifier = modifier.height(36.dp))
        UserTypeField(selectecUserType = state.userType, onUserTypeSelect = onUserTypeSelect)
        WithdrawButton(onClick = onWithdraw)
        Spacer(modifier = modifier.weight(1f))
        SachoSaengButton(
            enabled = state.canSave,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.save_label),
            onClick = { saveUserInfo() }
        )
    }
}

@Composable
fun ProfileImage(userType: UserType) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    )
    {
        Image(
            modifier = Modifier
                .size(127.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(bottom = 16.dp),
            painter = painterResource(id = userType.userTypeImageRes),
            contentDescription = null
        )
        Text(
            text = stringResource(id = userType.userTypeLabelRes),
            fontSize = 18.sp,
            fontWeight = FontWeight.W700
        )
    }
}

@Composable
private fun FieldTitle(title: String) {
    Text(
        modifier = Modifier.padding(bottom = 14.dp),
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.W700
    )
}

@Composable
private fun NickNameField(nickName: String, onNicknameChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        FieldTitle(stringResource(id = R.string.mypage_nickname_field_label))
        BasicTextField(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = Gs_White)
                .padding(16.dp)
                .fillMaxWidth(),
            value = nickName,
            onValueChange = { onNicknameChange(it) })
    }
}


@Composable
private fun UserTypeField(
    modifier: Modifier = Modifier,
    selectecUserType: UserType,
    onUserTypeSelect: (UserType) -> Unit
) {
    Column {
        FieldTitle(stringResource(id = R.string.mypage_usertype_field_label))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            UserTypeCard(
                modifier = modifier.weight(1f),
                isSelected = selectecUserType == UserType.STUDENT,
                userType = UserType.STUDENT,
                onUserTypeSelect = onUserTypeSelect
            )
            UserTypeCard(
                modifier = modifier.weight(1f),
                isSelected = selectecUserType == UserType.JOBSEEKER,
                userType = UserType.JOBSEEKER,
                onUserTypeSelect = onUserTypeSelect
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            UserTypeCard(
                modifier = modifier.weight(1f),
                isSelected = selectecUserType == UserType.NEWCOMER,
                userType = UserType.NEWCOMER,
                onUserTypeSelect = onUserTypeSelect
            )
            UserTypeCard(
                modifier = modifier.weight(1f),
                isSelected = selectecUserType == UserType.ETC,
                userType = UserType.ETC,
                onUserTypeSelect = onUserTypeSelect
            )
        }

    }
}

@Composable
private fun UserTypeCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    userType: UserType,
    onUserTypeSelect: (UserType) -> Unit
) {
    Card(
        modifier = modifier
            .height(48.dp)
            .noRippleClickable { onUserTypeSelect(userType) },
        colors = CardDefaults.cardColors().copy(
            containerColor = Gs_White,
        ),
        border = BorderStroke(width = 1.dp, color = if (isSelected) Gs_Black else Gs_White)

    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                fontSize = 15.sp,
                text = stringResource(id = userType.userTypeLabelRes),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_unchecked),
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (isSelected) Gs_Black else Gs_G3)
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
            .noRippleClickable {
                onClick()
            }
            .padding(top = 20.dp)
    )
}

@Preview
@Composable
fun ModifyUserInfoScreenPreview() {
    ModifyUserInfoScreen(
        state = ModifiyUserInfoUiState(
            userName = "",
            userType = UserType.JOBSEEKER,
            canSave = false
        ),
        onNicknameChange = {},
        onUserTypeSelect = {},
        navigateToBackStack = {},
        modifier = Modifier.background(Color.White)
    )
}