package com.example.sachosaeng.main;

import androidx.lifecycle.ViewModel
import com.example.sachosaeng.core.usecase.auth.GetAccessTokenUsecase
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
        checkHasAccessToken()
    }

    private fun checkHasAccessToken() = intent {
        if (getAccessTokenUsecase().isNotEmpty())
            postSideEffect(AppSideEffect.NavigateToDeepLink)
    }
}

data class AppUiState(
    val snackBarMessage: String? = null
)

sealed class AppSideEffect {
    data object NavigateToDeepLink : AppSideEffect()
}