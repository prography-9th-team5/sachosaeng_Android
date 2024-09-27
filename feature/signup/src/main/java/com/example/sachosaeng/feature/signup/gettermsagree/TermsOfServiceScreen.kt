package com.example.sachosaeng.feature.signup.gettermsagree

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sachosaeng.core.ui.R
import com.example.sachosaeng.core.ui.component.button.SachoSaengButton
import com.example.sachosaeng.core.ui.noRippleClickable
import com.example.sachosaeng.core.ui.theme.Gs_Black
import com.example.sachosaeng.core.ui.theme.Gs_G3
import com.example.sachosaeng.core.ui.theme.Gs_G4
import com.example.sachosaeng.core.ui.theme.Gs_G6
import com.example.sachosaeng.core.ui.theme.Gs_White
import com.example.sachosaeng.core.usecase.auth.LoginUsecase
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun TermsOfServiceScreen(
    navigateToServiceTermsDetail: () -> Unit,
    navigateToPersonalInformationTerm: () -> Unit,
    navigateToSocialLogin: () -> Unit,
    viewModel: TermsOfServiceViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            is TermsOfServiceSideEffect.MoveToSocialLogin -> navigateToSocialLogin()
        }
    }

    TermsOfServiceScreen(
        navigateToServiceTermsDetail = navigateToServiceTermsDetail,
        navigateToPersonalInformationTerm = navigateToPersonalInformationTerm,
        onAgreeAllRequiredTerms = viewModel::onConsentToAllTermsChanged,
        onAgreePersonalInformation = viewModel::onConsentToCollectPersonalInfoChanged,
        onAgreeServiceTerms = viewModel::onConsentToTermsOfServiceChanged,
        onConfirm = viewModel::onConfirm,
        state = state
    )
}

@Composable
internal fun TermsOfServiceScreen(
    onAgreeAllRequiredTerms: () -> Unit,
    onAgreePersonalInformation: () -> Unit,
    navigateToServiceTermsDetail: () -> Unit,
    navigateToPersonalInformationTerm: () -> Unit,
    onAgreeServiceTerms: () -> Unit,
    onConfirm: () -> Unit,
    state: TermsOfServiceUiState
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 40.dp, top = 70.dp),
            text = stringResource(id = R.string.terms_of_service_screen_title),
            fontSize = 26.sp,
            fontWeight = FontWeight.W700,
        )
        TermSelectBox(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Gs_G3),
            term = stringResource(id = R.string.agree_all_required_terms),
            isSelected = state.isConsentToAllTerms,
            onSelect = onAgreeAllRequiredTerms
        )
        TermSelectBox(
            term = stringResource(id = R.string.agree_personal_information),
            isSelected = state.isConsentToCollectPersonalInfo,
            onSelect = onAgreePersonalInformation,
            navigateToTermDetail = navigateToPersonalInformationTerm
        )
        TermSelectBox(
            term = stringResource(id = R.string.agree_service_terms),
            isSelected = state.isConsentToTermsOfService,
            onSelect = onAgreeServiceTerms,
            navigateToTermDetail = navigateToServiceTermsDetail
        )
        Spacer(modifier = Modifier.weight(1f))
        SachoSaengButton(
            enabled = state.isConfirmButtonEnable,
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(vertical = 16.dp),
            text = stringResource(id = R.string.confirm_label),
            onClick = onConfirm
        )
    }
}

@Composable
fun TermSelectBox(
    modifier: Modifier = Modifier,
    term: String,
    navigateToTermDetail: () -> Unit = { },
    isSelected: Boolean = false,
    onSelect: () -> Unit = { }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_unchecked),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                if (isSelected) Gs_G6 else Gs_G4
            )
        )
        Text(
            modifier = Modifier.weight(1f),
            text = term,
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.W500 else FontWeight.W400,
        )
        Image(
            modifier = Modifier.noRippleClickable { navigateToTermDetail() },
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                if (isSelected) Gs_G6 else Gs_G4
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTermsOfServiceScreen() {
    TermsOfServiceScreen(
        navigateToServiceTermsDetail = { },
        navigateToPersonalInformationTerm = { },
        onAgreeAllRequiredTerms = { },
        onAgreePersonalInformation = { },
        onAgreeServiceTerms = { },
        onConfirm = { },
        state = TermsOfServiceUiState()
    )
}