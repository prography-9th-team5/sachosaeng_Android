package com.sachosaeng.app.feature.signup.signupcomplete

import androidx.lifecycle.ViewModel
import com.sachosaeng.app.core.ui.UserType
import com.sachosaeng.app.core.usecase.auth.LoginUsecase
import com.sachosaeng.app.core.usecase.user.GetMyInfoUsecase
import com.sachosaeng.app.core.usecase.user.GetUserTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpCompleteViewModel @Inject constructor(
    val getMyInfoUsecase: GetMyInfoUsecase,
    val getUserTypeUseCase: GetUserTypeUseCase,
    val loginUsecase: LoginUsecase
) : ViewModel(),
    ContainerHost<SignUpCompleteUiState, Unit> {
    override val container: Container<SignUpCompleteUiState, Unit> =
        container(SignUpCompleteUiState())

    init {
        showSignUpCompleteSplash()
        login()
    }

    private fun showSignUpCompleteSplash() = intent {
        val userName = getMyInfoUsecase().firstOrNull()?.name ?: ""
        val userType = getUserTypeUseCase().firstOrNull()?: ""
        reduce {
            state.copy(
                userName = userName,
                userType = UserType.getType(userType) ?: UserType.NEW_EMPLOYEE,
                isShow = true
            )
        }
    }

    private fun login() = intent {
        loginUsecase(state.userName).collectLatest {
            delay(2000)
            reduce {
                state.copy(isShow = false)
            }
        }
    }
}