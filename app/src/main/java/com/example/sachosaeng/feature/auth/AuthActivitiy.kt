package com.example.sachosaeng.feature.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sachosaeng.BuildConfig
import com.example.sachosaeng.R
import com.example.sachosaeng.main.MainActivity
import com.example.sachosaeng.ui.theme.SachosaengTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivitiy : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    val googleSignInOptions = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.GOOGLE_OAUTH_KEY)
        .requestEmail()
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SachosaengTheme {
                Surface {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        kakaoLoginButton()
                        googleLoginButton()
                    }
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
                        AuthUiState.LoginSuccess -> navigateToMainActivity()
                        AuthUiState.LoginFail -> Log.e("LoginFailed", "Login Failed")
                        else -> {}
                    }
                }
            }
        }
    }

    @Composable
    fun kakaoLoginButton() {
        return Image(
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.kakao_login_medium_wide),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    authViewModel.handleKakaoLogin(
                        activity = this@AuthActivitiy,
                        onSuccess = { authViewModel.loginSuccess() },
                        onFailure = { authViewModel.loginFail() }
                    )
                }
                .width(200.dp)
                .height(50.dp),
        )
    }

    @Composable
    fun googleLoginButton() {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) {
            authViewModel.requestGoogleLogin(activityResult = it) {
                authViewModel.loginSuccess()
            }
        }
        val googleSignInClient = GoogleSignIn.getClient(this@AuthActivitiy, googleSignInOptions)

        return Image(
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.web_light_sq),
            contentDescription = "",
            modifier = Modifier
                .clickable { launcher.launch(googleSignInClient.signInIntent) }
                .width(200.dp)
                .height(50.dp)
        )
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}