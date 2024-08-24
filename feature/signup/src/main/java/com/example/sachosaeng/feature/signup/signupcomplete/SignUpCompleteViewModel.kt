package com.example.sachosaeng.feature.signup.signupcomplete

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sachosaeng.core.ui.UserType
import com.example.sachosaeng.core.usecase.auth.GetEmailUsecase
import com.example.sachosaeng.core.usecase.user.GetUserTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpCompleteViewModel @Inject constructor(
    val getUserEmailUsecase: GetEmailUsecase,
    val getUserTypeUseCase: GetUserTypeUseCase
) : ViewModel(),
    ContainerHost<SignUpCompleteUiState, Unit> {
    override val container: Container<SignUpCompleteUiState, Unit> =
        container(SignUpCompleteUiState())

    init {
        showSignUpCompleteSplash()
        hideSignUpCompleteSplash()
    }

    private fun showSignUpCompleteSplash() = intent {
        val userName = getUserEmailUsecase().first()
        val userType = getUserTypeUseCase().first()
        reduce {
            state.copy(
                userName = userName,
                userType = UserType.getType(userType) ?: UserType.NEW_EMPLOYEE,
                isShow = true
            )
        }
    }

    private fun hideSignUpCompleteSplash() = intent {
        delay(2000)
        reduce {
            state.copy(isShow = false)
        }
    }
}