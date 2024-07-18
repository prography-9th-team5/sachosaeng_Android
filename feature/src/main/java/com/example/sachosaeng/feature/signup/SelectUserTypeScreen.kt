package com.example.sachosaeng.feature.signup

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G6
import com.example.sachosaeng.feature.R
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SelectUserTypeScreen(
    viewModel: SelectUserTypeViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()

    SelectUserTypeScreen(
        uiState = state.value,
        moveToNextStep = {},
        changeSelectUserType = { viewModel::changeSelectUserType })
}

@Composable
internal fun SelectUserTypeScreen(
    uiState: SelectUserTypeUiState,
    moveToNextStep: () -> Unit = {},
    changeSelectUserType: (UserType) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
    ) {
        SignUpProgressBar()
        SelectUserTypeText()
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
            text = stringResource(id = R.string.next),
            onClick = { moveToNextStep() }
        )
    }
}

@Composable
fun SelectUserTypeText() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(id = R.string.select_user_type_label),
            fontSize = 26.sp,
            fontWeight = FontWeight.W700
        )
        Text(
            text = stringResource(id = R.string.select_user_type_desc),
            fontSize = 16.sp,
            color = Gs_G6,
            fontWeight = FontWeight.W500
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
            offImageResource = R.drawable.ic_user_type_student_off,
            onImageResource = R.drawable.ic_user_type_student_on,
            userTypeLabel = R.string.user_type_student
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.JOBSEEKER) },
            isSelected = selectedType == UserType.JOBSEEKER,
            userType = UserType.JOBSEEKER,
            offImageResource = R.drawable.ic_user_type_jobseeker_off,
            onImageResource = R.drawable.ic_user_type_jobseeker_on,
            userTypeLabel = R.string.user_type_jobseeker
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.NEWCOMER) },
            isSelected = selectedType == UserType.NEWCOMER,
            userType = UserType.NEWCOMER,
            offImageResource = R.drawable.ic_user_type_newcomer_off,
            onImageResource = R.drawable.ic_user_type_newcomer_on,
            userTypeLabel = R.string.user_type_newcomer
        )
        UserTypeCard(
            onSelect = { onSelect(UserType.ETC) },
            isSelected = selectedType == UserType.ETC,
            userType = UserType.ETC,
            offImageResource = R.drawable.ic_user_type_etc_off,
            onImageResource = R.drawable.ic_user_type_etc_on,
            userTypeLabel = R.string.user_type_etc
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