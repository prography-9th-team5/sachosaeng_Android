package com.sachosaeng.app.feature.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.sachosaeng.app.core.domain.constant.OAuthType
import com.sachosaeng.app.core.ui.R
import com.sachosaeng.app.core.ui.R.drawable
import com.sachosaeng.app.core.ui.component.dialog.SachosaengOneButtonDialog
import com.sachosaeng.app.core.ui.component.snackbar.SachoSaengSnackbar
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G1
import com.sachosaeng.app.core.ui.theme.Gs_G3
import com.sachosaeng.app.core.ui.theme.Gs_G4
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.core.ui.theme.Kakao_Yellow
import com.sachosaeng.app.core.ui.theme.SachosaengTheme
import com.sachosaeng.app.core.util.constant.NavigationConstant.Main.MAIN_DEEP_LINK
import com.sachosaeng.app.core.util.constant.NavigationConstant.SignUp.SIGNUP_DEEP_LINK
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


//todo : refactoring
@AndroidEntryPoint
class AuthActivitiy : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.GOOGLE_OAUTH_KEY)
        .requestEmail().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var dialogMessage by remember { mutableStateOf<String?>(null) }
            var snackbarMessage by remember { mutableStateOf<String?>(null) }
            val state by authViewModel.collectAsState()

            authViewModel.collectSideEffect {
                when (it) {
                    is AuthSideEffect.NavigateToMain -> {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(MAIN_DEEP_LINK)
                        }
                        startActivity(intent)
                    }

                    is AuthSideEffect.NavigateToSignUp -> {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(SIGNUP_DEEP_LINK)
                        }
                        startActivity(intent)
                    }

                    is AuthSideEffect.ShowSnackbar -> {
                        snackbarMessage = it.message
                    }

                    is AuthSideEffect.ShowErrorDialog -> {
                        dialogMessage = it.message
                    }
                }
            }
            SachosaengTheme {
                Surface {
                    AuthScreen(recentAuthType = state.recentAuthType)
                }
                dialogMessage?.let {
                    LoginFailedDialog(
                        onConfirm = {
                            authViewModel.navigateToSignUp()
                        },
                        errorMessage = it
                    )
                }
                snackbarMessage?.let {
                    SachoSaengSnackbar(
                        Modifier.padding(bottom = 60.dp),
                        icon = {
                            Image(
                                painter = painterResource(id = drawable.ic_warning_black_small),
                                contentDescription = null
                            )
                        }, message = it, onDismiss = { snackbarMessage = null })
                }
            }
        }
    }

    @Composable
    fun LoginFailedDialog(
        modifier: Modifier = Modifier,
        onConfirm: () -> Unit,
        errorMessage: String
    ) {
        SachosaengOneButtonDialog(
            modifier = modifier,
            buttonText = stringResource(id = R.string.navigate_to_signup_text),
            buttonOnClick = { onConfirm() }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(vertical = 36.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Image(
                    painter = painterResource(id = drawable.ic_warning_black_small),
                    contentDescription = null
                )
                Text(
                    modifier = modifier.padding(top = 12.dp),
                    textAlign = TextAlign.Center,
                    text = errorMessage,
                    color = Gs_Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }
    }

    @Composable
    fun AuthScreen(recentAuthType: OAuthType) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Gs_White)
                .padding(horizontal = 20.dp),
        ) {
            item {
                Text(
                    modifier = Modifier.padding(bottom = 20.dp, top = 70.dp),
                    text = stringResource(id = R.string.select_account_platform_screen_title),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W700,
                    color = Gs_Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.select_account_platform_screen_description),
                    fontSize = 16.sp,
                    color = Gs_G6,
                    fontWeight = FontWeight.W500,
                )
            }
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 62.dp)
                        .size(280.dp)
                        .background(Gs_G3, RoundedCornerShape(4.dp))
                        .padding(55.dp),
                    painter = painterResource(id = R.drawable.image_todays_vote_dialog),
                    contentDescription = null
                )
            }
            item {
                Spacer(modifier = Modifier.size(62.dp))
                KakaoLoginButton(recentAuthType == OAuthType.KAKAO)
                Spacer(modifier = Modifier.size(8.dp))
                GoogleLoginButton(recentAuthType == OAuthType.GOOGLE)
            }
        }
    }

    @Composable
    fun KakaoLoginButton(isRecentLoginType: Boolean) {
        return Box() {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .background(Kakao_Yellow)
                    .clickable {
                        authViewModel.handleKakaoLogin(
                            activity = this@AuthActivitiy,
                            onFailure = { t -> authViewModel.loginFail(t) }
                        )
                    }) {
                Image(
                    alignment = Alignment.BottomCenter,
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = drawable.ic_kakao),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(28.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Gs_Black,
                    text = stringResource(id = R.string.login_with_kakao),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 18.dp)
                )
            }
            if (isRecentLoginType) Image(
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.Fit,
                painter = painterResource(id = drawable.ic_recent_login),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 37.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }

    @Composable
    fun GoogleLoginButton(isRecentLoginType: Boolean) {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { authViewModel.requestGoogleLogin(activityResult = it) }

        val googleSignInClient = GoogleSignIn.getClient(this@AuthActivitiy, options)

        return Box() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gs_G1)
                    .border(1.dp, Gs_G4, RoundedCornerShape(4.dp))
                    .clickable {
                        launcher.launch(googleSignInClient.signInIntent)
                    }
            ) {
                Image(
                    alignment = Alignment.BottomCenter,
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = drawable.ic_google),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(28.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Gs_Black,
                    text = stringResource(id = R.string.login_with_google),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 18.dp)
                )
            }
            if (isRecentLoginType) Image(
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.Fit,
                painter = painterResource(id = drawable.ic_recent_login),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 37.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }

    @Composable
    @Preview
    fun PreviewAuthScreen() {
        SachosaengTheme {
            AuthScreen(recentAuthType = OAuthType.GOOGLE)
        }
    }
}
