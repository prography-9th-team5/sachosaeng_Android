package com.example.sachosaeng.feature.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val _socialLoginUiState = MutableStateFlow<AuthUiState>(AuthUiState.IDle)
    val socialLoginUiState = _socialLoginUiState.asStateFlow()

    fun kakaoLogin() {
        _socialLoginUiState.value = AuthUiState.KakaoLogin
    }

    fun kakaoSignUp() {
        _socialLoginUiState.value = AuthUiState.KakaoSignUp
    }

    fun kakaoLoginSuccess() = _socialLoginUiState.tryEmit(AuthUiState.LoginSuccess)
    fun kakaoLoginFail() = _socialLoginUiState.tryEmit(AuthUiState.LoginFail)
    fun kakaoSignUpSuccess() = _socialLoginUiState.tryEmit(AuthUiState.LoginSuccess)
    fun kakaoSignUpFail() = _socialLoginUiState.tryEmit(AuthUiState.LoginFail)
    fun setUiStateIdle() = _socialLoginUiState.tryEmit(AuthUiState.IDle)

}

sealed interface AuthUiState {
    object LoginSuccess : AuthUiState
    object LoginFail : AuthUiState
    object IDle : AuthUiState
    object KakaoLogin : AuthUiState
    object KakaoSignUp : AuthUiState
}