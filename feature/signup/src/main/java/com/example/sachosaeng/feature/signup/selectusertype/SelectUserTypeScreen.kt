package com.example.sachosaeng.feature.signup.selectusertype

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.component.topappbar.SachosaengDetailTopAppBar
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.feature.signup.R.drawable
import com.example.sachosaeng.feature.signup.SelectScreenDescription
import com.example.sachosaeng.feature.signup.SignUpProgressBar
import com.example.sachosaeng.feature.signup.SignUpProgressbarWithColor
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SelectUserTypeScreen(
    moveToNextStep: () -> Unit,
    viewModel: SelectUserTypeViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            is SelectUserTypeSideEffect.MoveToSelectCategoryScreen -> { moveToNextStep() }
        }
    }

    SelectUserTypeScreen(
        uiState = state.value,
        moveToNextStep = viewModel::saveSelectedUserTypeAndMoveToNext,
        changeSelectUserType = viewModel::changeSelectUserType
    )
}

@Composable
internal fun SelectUserTypeScreen(
    modifier: Modifier = Modifier,
    uiState: SelectUserTypeUiState,
    moveToNextStep: () -> Unit = {},
    changeSelectUserType: (UserType) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
    ) {
        SachosaengDetailTopAppBar(
            modifier = modifier,
            title = stringResource(id = string.select_user_type_screen_title),
            fontWeight = FontWeight.W500,
            fontSize = 16
        )
        SelectUserTypeProgressbar()
        SelectScreenDescription(
            title = stringResource(id = string.select_user_type_label),
            subText = stringResource(id = string.select_user_type_desc)
        )
        Spacer(modifier = modifier.height(45.dp))
        UserTypeList(
            modifier = modifier.weight(1f),
            onSelect = {
                changeSelectUserType(it)
            },
            selectedType = uiState.selectedType
        )
        SachoSaengButton(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.End),
            text = stringResource(id = string.next),
            onClick = { moveToNextStep() }
        )
    }
}

@Composable
private fun SelectUserTypeProgressbar() {
    Row(modifier = Modifier.fillMaxWidth()) {
        SignUpProgressBar(
            modifier = Modifier.weight(0.5f)
        )
        SignUpProgressbarWithColor(color = Gs_G3, modifier = Modifier.padding(start = 20.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserTypeList(modifier: Modifier, selectedType: UserType, onSelect: (UserType) -> Unit) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        UserTypeCard(
            onSelect = { onSelect(UserType.STUDENT) },
            isSelected = selectedType == UserType.STUDENT,
            userType = UserType.STUDENT,
            imageResource = drawable.ic_user_type_student_on,
            userTypeLabel = string.user_type_student
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.JOBSEEKER) },
            isSelected = selectedType == UserType.JOBSEEKER,
            userType = UserType.JOBSEEKER,
            imageResource = drawable.ic_user_type_jobseeker_on,
            userTypeLabel = string.user_type_jobseeker
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.NEWCOMER) },
            isSelected = selectedType == UserType.NEWCOMER,
            userType = UserType.NEWCOMER,
            imageResource = drawable.ic_user_type_newcomer_on,
            userTypeLabel = string.user_type_newcomer
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.ETC) },
            isSelected = selectedType == UserType.ETC,
            userType = UserType.ETC,
            imageResource = drawable.ic_user_type_etc_on,
            userTypeLabel = string.user_type_etc
        )
    }
}

@Composable
fun UserTypeCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    userType: UserType,
    imageResource: Int,
    userTypeLabel: Int,
    onSelect: (UserType) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.noRippleClickable {
            onSelect(userType)
        }) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.48f)
                .border(
                    width = 1.dp,
                    color = if (isSelected) Gs_Black else Gs_White,
                    shape = RoundedCornerShape(4.dp)
                ),
            painter = painterResource(id = imageResource),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Text(
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.W700 else FontWeight.W500,
            text = stringResource(id = userTypeLabel)
        )
    }

}

@Composable
@Preview
fun SelectUserTypeScreenPreview() {
    SelectUserTypeScreen(
        uiState = SelectUserTypeUiState(),
        moveToNextStep = {},
        changeSelectUserType = {}
    )
}