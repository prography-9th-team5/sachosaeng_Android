package com.sachosaeng.app

import android.app.Application
import android.os.Build
import android.util.Log
import com.example.sachosaeng.SachoSaengFcmService.Companion.FCM_LOG_TAG
import com.example.sachosaeng.core.usecase.push.SaveFcmTokenUseCase
import com.example.sachosaeng.core.util.FirebaseUtil.getCurrentFcmToken
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class App: Application() {
    @Inject
    lateinit var saveFcmTokenUseCase: SaveFcmTokenUseCase
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            System.setProperty("dalvik.vm.dex2oat-flags", "--compiler-filter=speed")
        }
        
        FirebaseApp.initializeApp(this)
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)

        applicationScope.launch {
            try {
                val token = getCurrentFcmToken()
                if (token.isNotBlank()) {
                    saveFcmTokenUseCase(token)
                }
            } catch (e: Exception) {
                Log.e(FCM_LOG_TAG, e.toString())
            }
        }
    }
}
