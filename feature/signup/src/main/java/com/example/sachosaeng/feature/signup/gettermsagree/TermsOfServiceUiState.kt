package com.example.sachosaeng.feature.signup.gettermsagree

data class TermsOfServiceUiState(
    val isConsentToAllTerms: Boolean = false,
    val isConsentToTermsOfService : Boolean = false,
    val isConsentToCollectPersonalInfo : Boolean = false,
    val isConfirmButtonEnable : Boolean = false
)

