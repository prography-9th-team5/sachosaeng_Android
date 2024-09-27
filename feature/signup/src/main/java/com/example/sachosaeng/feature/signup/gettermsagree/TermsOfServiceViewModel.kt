package com.example.sachosaeng.feature.signup.gettermsagree

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TermsOfServiceViewModel @Inject constructor() : ViewModel(), ContainerHost<TermsOfServiceUiState, TermsOfServiceSideEffect> {
    override val container: Container<TermsOfServiceUiState, TermsOfServiceSideEffect> =
        container(TermsOfServiceUiState())

    fun onConsentToAllTermsChanged() = intent {
        val isConsentToAllTerms = state.isConsentToAllTerms
        reduce {
            state.copy(
                isConsentToAllTerms = !isConsentToAllTerms,
                isConsentToCollectPersonalInfo = !isConsentToAllTerms,
                isConsentToTermsOfService = !isConsentToAllTerms
            )
        }
        checkIsAllTermsChecked()
    }

    fun onConsentToTermsOfServiceChanged() = intent {
        reduce { state.copy(isConsentToTermsOfService = !state.isConsentToTermsOfService) }
        checkIsAllTermsChecked()
    }

    fun onConsentToCollectPersonalInfoChanged() = intent {
        reduce { state.copy(isConsentToCollectPersonalInfo = !state.isConsentToCollectPersonalInfo) }
        checkIsAllTermsChecked()
    }

    private fun checkIsAllTermsChecked() = intent {
        val isAllTermsChecked =
            state.isConsentToTermsOfService && state.isConsentToCollectPersonalInfo
        reduce {
            state.copy(
                isConfirmButtonEnable = isAllTermsChecked,
                isConsentToAllTerms = isAllTermsChecked
            )
        }
    }

    fun onConfirm() = intent {
        if (state.isConsentToAllTerms) {
            postSideEffect(TermsOfServiceSideEffect.MoveToSocialLogin)
        }
    }
}

sealed class TermsOfServiceSideEffect {
    data object MoveToSocialLogin : TermsOfServiceSideEffect()
}