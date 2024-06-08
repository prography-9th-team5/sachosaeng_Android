package com.example.sachosaeng.feature.auth

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import com.example.sachosaeng.feature.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val _socialLoginUiState = MutableStateFlow<AuthUiState>(AuthUiState.IDle)
    val socialLoginUiState = _socialLoginUiState.asStateFlow()
    val googleSignInOptions = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.GOOGLE_OAUTH_KEY)
        .requestEmail()
        .build()
    fun loginSuccess() = _socialLoginUiState.tryEmit(AuthUiState.LoginSuccess)
    fun loginFail() = _socialLoginUiState.tryEmit(AuthUiState.LoginFail)
    fun setUiStateIdle() = _socialLoginUiState.tryEmit(AuthUiState.IDle)
    fun requestGoogleLogin(
        activityResult: ActivityResult,
        onSuccess: () -> Unit
    ) {
        try {
            val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                .getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task -> if (task.isSuccessful) onSuccess() }
        } catch (e: Exception) {
            Log.e("GoogleLogin", e.toString())
        }
    }

    fun loginWithKakaoTalk(
        activity: Activity,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        UserApiClient.instance.loginWithKakaoTalk(activity) { token, error ->
            error?.let(onFailure)
            token?.let { onSuccess() }
        }
    }

    fun loginWithKakaoAccount(
        activity: Activity,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        UserApiClient.instance.loginWithKakaoAccount(activity) { token, error ->
            error?.let(onFailure)
            token?.let { onSuccess() }
        }
    }

    fun handleKakaoLogin(
        activity: Activity,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(activity)) loginWithKakaoTalk(
            activity,
            onSuccess,
            onFailure
        )
        else loginWithKakaoAccount(activity, onSuccess, onFailure)
    }
}

sealed interface AuthUiState {
    object LoginSuccess : AuthUiState
    object LoginFail : AuthUiState
    object IDle : AuthUiState
}