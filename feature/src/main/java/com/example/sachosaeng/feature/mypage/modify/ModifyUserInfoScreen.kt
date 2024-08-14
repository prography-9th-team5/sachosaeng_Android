package com.example.sachosaeng.feature.mypage.modify

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.feature.R
import com.example.sachosaeng.feature.signup.selectusertype.UserType
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ModifyUserInfoScreen(
    navigateToBackStack: () -> Unit = {},
    viewModel: ModifyUserInfoViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    ModifyUserInfoScreen(
        state = state,
        onNicknameChange = viewModel::onNickNameChange,
        onUserTypeSelect = viewModel::onUserTypeSelect,
        onWithdraw = viewModel::onWithdraw,
        navigateToBackStack = navigateToBackStack,
    )
}

@Composable
internal fun ModifyUserInfoScreen(
    state: ModifiyUserInfoUiState,
    onNicknameChange: (String) -> Unit,
    onUserTypeSelect: (UserType) -> Unit,
    onWithdraw:() -> Unit = {},
    navigateToBackStack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        SachosaengDetailTopAppBar(
            title = stringResource(id = R.string.mypage_modify_userinfo),
            navigateToBackStack = navigateToBackStack
        )
        ProfileImage()
        NickNameField(state.userName, onNicknameChange = onNicknameChange)
        UserTypeField(selectecUserType = state.userType, onUserTypeSelect = onUserTypeSelect)
        WithdrawButton()
    }
}

@Composable
fun ProfileImage(userType: UserType) {
    Image(
        modifier = Modifier.size(127.dp),
        painter = painterResource(id = userType.userTypeImageRes),
        contentDescription = null
    )
}

@Composable
private fun FieldTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.W700
    )
}

@Composable
private fun NickNameField(nickName: String, onNicknameChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        FieldTitle(stringResource(id = R.string.mypage_nickname_field_label))
        OutlinedTextField(value = nickName, onValueChange = { onNicknameChange(it) })
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
        Row(modifier = modifier.fillMaxWidth()) {
            UserTypeCard(
                isSelected = selectecUserType == UserType.STUDENT,
                userType = UserType.STUDENT,
                onUserTypeSelect = onUserTypeSelect
            )
            UserTypeCard(
                isSelected = selectecUserType == UserType.JOBSEEKER,
                userType = UserType.JOBSEEKER,
                onUserTypeSelect = onUserTypeSelect
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            UserTypeCard(
                isSelected = selectecUserType == UserType.NEWCOMER,
                userType = UserType.NEWCOMER,
                onUserTypeSelect = onUserTypeSelect
            )
            UserTypeCard(
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
            .padding(8.dp)
            .noRippleClickable { onUserTypeSelect(userType) },
        colors = CardDefaults.cardColors().copy(
            containerColor = Gs_White,
        ),
        border = BorderStroke(width = 1.dp, color = if (isSelected) Gs_Black else Gs_White)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .wrapContentWidth()
                .padding(vertical = 10.dp, horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = userType.userTypeLabelRes),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_unchecked),
                contentDescription = null
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