package com.example.sachosaeng.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sachosaeng.core.usecase.auth.GetAccessTokenUsecase
import com.example.sachosaeng.core.usecase.auth.LoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val getAccessTokenUsecase: GetAccessTokenUsecase,
    val loginUsecase: LoginUsecase
) : ViewModel(), ContainerHost<Boolean, SplashSideEffect> {
    override val container: Container<Boolean, SplashSideEffect> = container(true)

    init {
        showSplash()
        checkIfUserJoined()
    }

    private fun showSplash() = intent {
        viewModelScope.launch {
            delay(1000)
            reduce { false }
        }
    }

    private fun checkIfUserJoined() = intent {
        loginUsecase(id = "testtest12@test").collectLatest {
            when (it) {
                true -> postSideEffect(SplashSideEffect.NavigateToHome)
                false -> postSideEffect(SplashSideEffect.NavigateToSignUp)
            }
        }
//        getAccessTokenUsecase().collectLatest {
//            when (it.isEmpty()) {
//                true -> postSideEffect(SplashSideEffect.NavigateToSignUp)
//                false -> postSideEffect(SplashSideEffect.NavigateToHome)
//            }
    }
}

sealed class SplashSideEffect {
    data object NavigateToHome : SplashSideEffect()
    data object NavigateToSignUp : SplashSideEffect()
}