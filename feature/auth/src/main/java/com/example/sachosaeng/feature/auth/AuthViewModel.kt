package com.sachosaeng.app.feature.auth

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.user.UserApiClient
import com.sachosaeng.app.core.domain.constant.OAuthType
import com.sachosaeng.app.core.usecase.auth.GetEmailUsecase
import com.sachosaeng.app.core.usecase.auth.GetRecentAuthTypeUseCase
import com.sachosaeng.app.core.usecase.auth.LoginUsecase
import com.sachosaeng.app.core.usecase.auth.SetEmailUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
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
class AuthViewModel @Inject constructor(
    val getEmailUsecase: GetEmailUsecase,
    val setEmailUsecase: SetEmailUsecase,
    val loginWithEmailUsecase: LoginUsecase,
    val getRecentAuthTypeUseCase: GetRecentAuthTypeUseCase
) : ViewModel(),
    ContainerHost<AuthUiState, AuthSideEffect> {
    override val container: Container<AuthUiState, AuthSideEffect> =
        container(AuthUiState())

    init {
        getRecentAuthType()
    }

    private fun getRecentAuthType() = intent {
        getRecentAuthTypeUseCase().collectLatest {
            reduce { state.copy(recentAuthType = it) }
        }
    }

    fun loginFail(throwable: Throwable) = intent {
        postSideEffect(AuthSideEffect.ShowSnackbar(throwable.message ?: "unknown error"))
    }

    fun requestGoogleLogin(
        activityResult: ActivityResult,
    ) {
        try {
            val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                .getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) handleGoogleLoginResult()
                    else loginFail(task.exception!!)
                }
        } catch (e: Exception) { }
    }

    private fun loginWithKakaoTalk(
        activity: Activity,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        UserApiClient.instance.loginWithKakaoTalk(activity) { token, error ->
            error?.let { onFailure(it) }
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

    private fun handleGoogleLoginResult() = intent {
        FirebaseAuth.getInstance().currentUser?.email?.let {
            setEmailUsecase(it, OAuthType.GOOGLE)
            checkLogin()
        }
    }

    private fun handleKakaoLogin() = intent {
        UserApiClient.instance.me { user, _ ->
            viewModelScope.launch {
                val email = user?.kakaoAccount?.email ?: ""
                setEmailUsecase(email, OAuthType.KAKAO)
                checkLogin()
            }
        }
    }

    private fun checkLogin() = intent {
        try {
            val email = getEmailUsecase()
            loginWithEmailUsecase(email).collectLatest { result ->
                when (result) {
                    true -> postSideEffect(AuthSideEffect.NavigateToMain)
                    false -> postSideEffect(AuthSideEffect.ShowSnackbar("로그인에 실패했습니다."))
                }
            }
        } catch (e: Exception) {
            logout()
            postSideEffect(AuthSideEffect.ShowErrorDialog(e.message ?: "unknown error"))
        }
    }

    private fun logout() = intent {
        getRecentAuthTypeUseCase().collectLatest {
            when (it) {
                OAuthType.GOOGLE -> {
                    FirebaseAuth.getInstance().currentUser?.delete()?.addOnCompleteListener {
                        FirebaseAuth.getInstance().signOut()
                    }
                }
                OAuthType.KAKAO -> UserApiClient.instance.unlink {}
                else -> {}
            }
        }
    }

    fun navigateToSignUp() = intent {
        postSideEffect(AuthSideEffect.NavigateToSignUp)
    }


    fun handleKakaoLogin(
        activity: Activity,
        onFailure: (Throwable) -> Unit
    ) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(activity))
            loginWithKakaoTalk(
                activity,
                { handleKakaoLogin() },
            ) {
                loginWithKakaoAccount(activity, { handleKakaoLogin() }, onFailure)
            }
        else loginWithKakaoAccount(activity, { handleKakaoLogin() }, onFailure)
    }
}

sealed class AuthSideEffect {
    data object NavigateToMain : AuthSideEffect()
    data object NavigateToSignUp : AuthSideEffect()
    data class ShowSnackbar(val message: String) : AuthSideEffect()
    data class ShowErrorDialog(val message: String) : AuthSideEffect()
}

data class AuthUiState(
    val recentAuthType: OAuthType = OAuthType.NONE
)