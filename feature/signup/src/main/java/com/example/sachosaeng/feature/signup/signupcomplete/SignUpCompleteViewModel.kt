package com.example.sachosaeng.feature.signup.signupcomplete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpCompleteViewModel @Inject constructor() : ViewModel(),
    ContainerHost<SignUpCompleteUiState, Unit> {
    override val container: Container<SignUpCompleteUiState, Unit> =
        container(SignUpCompleteUiState())

    init {
        showSignUpCompleteSplash()
    }

    private fun showSignUpCompleteSplash() = intent {
        viewModelScope.launch {
            delay(2000)
            reduce { state.copy(isShow = false) }
        }
    }
}