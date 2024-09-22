package com.example.sachosaeng.feature.mypage.withdraw

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.R.drawable
import com.example.sachosaeng.core.ui.R.string
import com.example.sachosaeng.core.ui.WithdrawReason
import com.example.sachosaeng.core.ui.component.DetailScreenTopbar
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.component.snackbar.SachoSaengSnackbar
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G2
import com.example.sachosaeng.core.ui.theme.Gs_G4
import com.example.sachosaeng.core.ui.theme.Gs_G5
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.feature.mypage.main.WithdrawDialog
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun WithdrawScreen(
    modifier: Modifier = Modifier,
    navigateToBackStack: () -> Unit = {},
    viewModel: WithdrawViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    var withdrawDialogShowState by remember { mutableStateOf(true) }

    viewModel.collectSideEffect {
        when (it) {
            is WithdrawSideEffect.ShowSnackbar -> {
                snackbarMessage = it.message
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getUserName()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Gs_White)
            .padding(16.dp)
            .padding(WindowInsets.ime.asPaddingValues())
    ) {
        LazyColumn {
            item {
                DetailScreenTopbar(
                    navigateToBackStack = { navigateToBackStack() }
                )
                WithdrawScreenTitleAndDescription(userName = state.userName)
                ReasonForWithdrawList(
                    onSelect = { viewModel.changeSelectedReason(it) },
                    selectedReason = state.selectedReason
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                if (state.reasonForWithdrawDetailFieldIsOpened) {
                    DetailReasonForWithdraw(
                        reasonForWithdrawDetail = state.reasonForWithdrawDetail,
                        onValueChanged = { viewModel.changeReasonForWithdrawDetail(it) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        SachoSaengButton(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(vertical = 16.dp),
            text = stringResource(id = string.confirm_label),
            onClick = { viewModel.withdraw() }

        )
    }
    snackbarMessage?.let {
        SachoSaengSnackbar(
            modifier.padding(bottom = 80.dp),
            message = it, onDismiss = { snackbarMessage = null }
        )
    }
    if (withdrawDialogShowState) {
        WithdrawDialog(
            onWithdraw = { withdrawDialogShowState = false },
            onCancel = navigateToBackStack
        )
    }
}

@Composable
fun WithdrawScreenTitleAndDescription(modifier: Modifier = Modifier, userName: String) {
    Column(modifier = modifier.padding(bottom = 45.dp)) {
        Text(
            text = stringResource(id = string.withdraw_screen_title, userName),
            fontSize = 26.sp,
            fontWeight = FontWeight.W700,
        )
        Text(
            text = stringResource(id = string.withdraw_screen_description),
            fontSize = 16.sp,
            fontWeight = FontWeight.W500
        )
    }
}


@Composable
fun ReasonForWithdrawList(
    onSelect: (WithdrawReason) -> Unit,
    selectedReason: WithdrawReason? = null
) {
    Column {
        MultiSelectBox(
            reason = WithdrawReason.DISSATISFACTION,
            onSelect = {
                onSelect(it)
            },
            selectedReason = selectedReason
        )
        MultiSelectBox(
            reason = WithdrawReason.LACK_OF_CONTENT,
            onSelect = {
                onSelect(it)
            },
            selectedReason = selectedReason
        )
        MultiSelectBox(
            reason = WithdrawReason.NO_LONGER_USE,
            onSelect = {
                onSelect(it)
            },
            selectedReason = selectedReason
        )
        MultiSelectBox(onSelect = {
            onSelect(it)
        }, reason = WithdrawReason.ETC, selectedReason = selectedReason)
    }
}

@Composable
fun DetailReasonForWithdraw(reasonForWithdrawDetail: String, onValueChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 75.dp),
        placeholder = {
            Text(
                text = stringResource(id = string.withdraw_reason_hint),
                fontSize = 16.sp,
                color = Gs_G5
            )
        },
        value = reasonForWithdrawDetail,
        onValueChange = { onValueChanged(it) },
        enabled = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Gs_G2,
            unfocusedContainerColor = Gs_G2,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Gs_Black
        )
    )
}

@Composable
fun MultiSelectBox(
    modifier: Modifier = Modifier,
    reason: WithdrawReason,
    selectedReason: WithdrawReason?,
    onSelect: (WithdrawReason) -> Unit = {}
) {
    val isSelected = selectedReason == reason
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp,
                color = if (isSelected) Gs_Black else Gs_White,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(color = Gs_White)
            .padding(vertical = 22.dp, horizontal = 16.dp)
            .noRippleClickable {
                onSelect(reason)
            }
    ) {
        Image(
            modifier = modifier.padding(end = 8.dp),
            painter = painterResource(id = drawable.ic_unchecked),
            contentDescription = null,
            colorFilter = ColorFilter.tint(if (isSelected) Gs_Black else Gs_G4)
        )
        Text(
            text = stringResource(id = reason.userTypeLabelRes),
            fontWeight = FontWeight.W500,
            fontSize = 18.sp
        )
    }
}

@Composable
@Preview
fun WithdrawScreenPreview() {
    WithdrawScreen(
        navigateToBackStack = {}
    )
}
