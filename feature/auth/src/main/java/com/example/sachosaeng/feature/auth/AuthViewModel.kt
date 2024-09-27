package com.example.sachosaeng.feature.auth

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sachosaeng.core.domain.constant.OAuthType
import com.example.sachosaeng.core.usecase.auth.LoginUsecase
import com.example.sachosaeng.core.usecase.auth.SetEmailUsecase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val setEmailUsecase: SetEmailUsecase,
    val loginWithEmailUsecase: LoginUsecase,
) : ViewModel(),
    ContainerHost<Unit, AuthSideEffect> {
    override val container: Container<Unit, AuthSideEffect> =
        container(Unit)

    fun loginFail(throwable: Throwable) = intent {
        postSideEffect(AuthSideEffect.ShowSnackbar(throwable.message ?: ""))
    }

    fun requestGoogleLogin(
        activityResult: ActivityResult,
    ) {
        try {
            val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                .getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task -> if (task.isSuccessful) handleLoginResult(OAuthType.GOOGLE) }
        } catch (e: Exception) {
            Log.e("GoogleLogin", e.toString())
        }
    }

    private fun loginWithKakaoTalk(
        activity: Activity,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        UserApiClient.instance.loginWithKakaoTalk(activity) { token, error ->
            error?.let(onFailure)
            token?.let { onSuccess() }
        }
    }

    private fun loginWithKakaoAccount(
        activity: Activity,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        UserApiClient.instance.loginWithKakaoAccount(activity) { token, error ->
            error?.let(onFailure)
            token?.let { onSuccess() }
        }
    }

    private fun handleLoginResult(type: OAuthType) = intent {
        when (type) {
            OAuthType.KAKAO -> {
                UserApiClient.instance.me { user, error ->
                    viewModelScope.launch {
                        val email = user?.kakaoAccount?.email ?: ""
                        setEmailUsecase(email)
                        checkLogin(email = email)
                    }
                }
            }

            OAuthType.GOOGLE -> {
                FirebaseAuth.getInstance().currentUser?.email?.let {
                    setEmailUsecase(it)
                    checkLogin(it)
                }
            }
        }
    }

    private fun checkLogin(email: String) = intent {
        loginWithEmailUsecase(email).collectLatest {
            if (it) postSideEffect(AuthSideEffect.NavigateToMain)
            else postSideEffect(AuthSideEffect.NavigateToSelectUserType)
        }
    }

    fun handleKakaoLogin(
        activity: Activity,
        onFailure: (Throwable) -> Unit
    ) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(activity))
            loginWithKakaoTalk(
                activity,
                { handleLoginResult(OAuthType.KAKAO) },
            ) {
                loginWithKakaoAccount(activity, { handleLoginResult(OAuthType.KAKAO) }, onFailure)
            }
        else loginWithKakaoAccount(activity, { handleLoginResult(OAuthType.KAKAO) }, onFailure)
    }
}

sealed class AuthSideEffect {
    data object NavigateToMain : AuthSideEffect()
    data object NavigateToSelectUserType : AuthSideEffect()
    data class LoginFail(val message: String) : AuthSideEffect()
    data object IDle : AuthSideEffect()
    data class ShowSnackbar(val message: String) : AuthSideEffect()
}