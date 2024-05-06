package com.example.sachosaeng.feature.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sachosaeng.R
import com.example.sachosaeng.main.MainActivity
import com.example.sachosaeng.ui.theme.SachosaengTheme
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivitiy : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SachosaengTheme {
                Surface {
                    Image(
                        alignment = Alignment.BottomCenter,
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.kakao_login_medium_wide),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable { handleKakaoLogin() }
                            .width(200.dp),
                    )
                }
            }
        }
        collectUiState()
    }

    private fun collectUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.socialLoginUiState.collect { uiState ->
                    when (uiState) {
                        AuthUiState.KakaoLogin -> handleKakaoLogin()
                        AuthUiState.LoginSuccess -> navigateToMainActivity()
                        AuthUiState.LoginFail -> Log.e("LoginFailed", "Login Failed")
                        else -> {}
                    }
                }
            }
        }
    }

    private fun handleKakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) loginWithKakaoTalk()
        else loginWithKakaoAccount()
    }

    private fun loginWithKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            handleLoginResult(token, error)
        }
    }

    private fun loginWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            handleLoginResult(token, error)
        }
    }

    private fun handleLoginResult(token: OAuthToken?, error: Throwable?) {
        error?.let {
            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) return
            authViewModel.kakaoLoginFail()
        }
        token?.let { authViewModel.kakaoLoginSuccess() }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}