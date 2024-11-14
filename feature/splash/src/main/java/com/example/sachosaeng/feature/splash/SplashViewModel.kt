package com.sachosaeng.app.feature.splash

import androidx.lifecycle.ViewModel
import com.sachosaeng.app.core.usecase.auth.GetAccessTokenUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val getAccessTokenUsecase: GetAccessTokenUsecase,
) : ViewModel(), ContainerHost<Boolean, SplashSideEffect> {
    override val container: Container<Boolean, SplashSideEffect> = container(true)

    init {
        checkHasAccessToken()
    }

    private fun checkHasAccessToken() = blockingIntent {
        when (getAccessTokenUsecase().isEmpty()) {
            true -> postSideEffect(SplashSideEffect.NavigateToLogin)
            false -> postSideEffect(SplashSideEffect.NavigateToHome)
        }
    }

    private fun checkLogin() = blockingIntent {
        when (getAccessTokenUsecase().isEmpty()) {
            true -> postSideEffect(SplashSideEffect.NavigateToLogin)
            false -> postSideEffect(SplashSideEffect.NavigateToHome)
        }
    }
}

sealed class SplashSideEffect {
    data object NavigateToHome : SplashSideEffect()
    data object NavigateToLogin : SplashSideEffect()
}