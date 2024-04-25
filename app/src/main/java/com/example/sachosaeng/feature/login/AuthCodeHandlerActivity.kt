package com.example.sachosaeng.feature.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sachosaeng.ui.theme.SachosaengTheme
import com.kakao.sdk.user.UserApiClient

class AuthCodeHandlerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            SachosaengTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(
                        onClick = { /* Do something! */ },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Button")
                    }
                }
            }
        }
    }

    fun login(){
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                // 카카오 로그인 실패 처리
                Log.e(TAG, "카카오 로그인 실패: ${error.message}")
            } else if (token != null) {
                val kakaoAccessToken = token.accessToken // 엑세스 토큰 얻기
                requestKakaoUserInfo()
                // 카카오 로그인 성공 처리
                Log.i(TAG, "카카오 로그인 성공")
                Log.i(TAG, kakaoAccessToken)
                requestKakaoUserInfo()
            }
        }
    }

    private fun requestKakaoUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                // 사용자 정보 요청 실패 처리
                Log.e(TAG, "사용자 정보 요청 실패: ${error.message}")
            } else if (user != null) {
                val userId = user.id
                val nickname = user.kakaoAccount?.profile?.nickname
                val isEmailVerified = user.kakaoAccount?.isEmailVerified ?: false

                // 이메일 미인증 시 동의창 띄우기
                if (!isEmailVerified) {
                    UserApiClient.instance.loginWithNewScopes(
                        this,
                        listOf("account_email")
                    ) { oAuthResponse, consentError ->
                        if (consentError != null) {
                            // 동의 실패 처리
                            Log.e(TAG, "동의 실패: ${consentError.message}")
                        } else {
                            // 동의 성공 처리
                            Log.i(TAG, "동의 성공")
                            // 동의창 띄운 후 추가 작업 수행
                            // 예를 들어, 사용자 정보 요청 등
                        }
                    }

                } else {
                    // 이미 이메일 인증된 사용자의 처리
                    Log.i(TAG, "이미 이메일 인증된 사용자")
                    // 추가 작업 수행
                }
            }
        }
    }
}