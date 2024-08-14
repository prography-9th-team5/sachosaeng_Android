package com.example.sachosaeng.feature.signup.selectusertype

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.feature.R.drawable
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.feature.signup.SignUpProgressBar
import com.example.sachosaeng.feature.signup.SelectScreenDescription
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SelectUserTypeScreen(
    moveToNextStep: () -> Unit,
    viewModel: SelectUserTypeViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()

    SelectUserTypeScreen(
        uiState = state.value,
        moveToNextStep = { moveToNextStep() },
        changeSelectUserType = {
            viewModel::changeSelectUserType.invoke(it)
        })
}

@Composable
internal fun SelectUserTypeScreen(
    uiState: SelectUserTypeUiState,
    moveToNextStep: () -> Unit = {},
    changeSelectUserType: (UserType) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
    ) {
        SignUpProgressBar(modifier = Modifier
            .fillMaxWidth(.5f)
            .padding(end = 10.dp))
        SelectScreenDescription(
            title = stringResource(id = string.select_user_type_label),
            subText = stringResource(id = string.select_user_type_desc)
        )
        Spacer(modifier = Modifier.height(45.dp))
        UserTypeList(
            modifier = Modifier.weight(1f),
            onSelect = {
                changeSelectUserType(it)
            },
            selectedType = uiState.selectedType
        )
        SachoSaengButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End),
            text = stringResource(id = string.next),
            onClick = { moveToNextStep() }
        )
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
            offImageResource = drawable.ic_user_type_student_off,
            onImageResource = drawable.ic_user_type_student_on,
            userTypeLabel = string.user_type_student
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.JOBSEEKER) },
            isSelected = selectedType == UserType.JOBSEEKER,
            userType = UserType.JOBSEEKER,
            offImageResource = drawable.ic_user_type_jobseeker_off,
            onImageResource = drawable.ic_user_type_jobseeker_on,
            userTypeLabel = string.user_type_jobseeker
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.NEWCOMER) },
            isSelected = selectedType == UserType.NEWCOMER,
            userType = UserType.NEWCOMER,
            offImageResource = drawable.ic_user_type_newcomer_off,
            onImageResource = drawable.ic_user_type_newcomer_on,
            userTypeLabel = string.user_type_newcomer
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.ETC) },
            isSelected = selectedType == UserType.ETC,
            userType = UserType.ETC,
            offImageResource = drawable.ic_user_type_etc_off,
            onImageResource = drawable.ic_user_type_etc_on,
            userTypeLabel = string.user_type_etc
        )
    }
}

@Composable
fun UserTypeCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    userType: UserType,
    onImageResource: Int,
    offImageResource: Int,
    userTypeLabel: Int,
    onSelect: (UserType) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.noRippleClickable {
            onSelect(userType)
        }) {
        Image(
            modifier = Modifier.fillMaxWidth(0.48f),
            painter = painterResource(id = if (isSelected) onImageResource else offImageResource),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Text(
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
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