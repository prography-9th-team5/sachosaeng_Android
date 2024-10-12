package com.sachosaeng.app.main

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.util.ErrorNotifier
import com.sachosaeng.app.core.usecase.auth.GetAccessTokenUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val getAccessTokenUsecase: GetAccessTokenUsecase,
) : ViewModel(), ContainerHost<AppUiState, AppSideEffect> {
    override val container: Container<AppUiState, AppSideEffect> =
        container(AppUiState())

    init {
        handleDeeplink()
        errorHandling()
    }

    private fun errorHandling() = intent {
        ErrorNotifier.errorFlow.collect { error ->
            postSideEffect(AppSideEffect.ShowSnackBar(error))
        }
    }

    private fun handleDeeplink() = intent {
        when (getAccessTokenUsecase().isEmpty()) {
            true -> postSideEffect(AppSideEffect.NavigateToAuthActivity)
            false -> postSideEffect(AppSideEffect.NavigateToDeepLink)
        }
    }
}

data class AppUiState(
    val snackBarMessage: String? = null
)

sealed class AppSideEffect {
    data object NavigateToAuthActivity : AppSideEffect()
    data object NavigateToDeepLink : AppSideEffect()
    data class ShowSnackBar(val message: String) : AppSideEffect()
}