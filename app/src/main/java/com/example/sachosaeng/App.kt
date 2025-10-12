package com.sachosaeng.app

import android.app.Application
import android.os.Build
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            System.setProperty("dalvik.vm.dex2oat-flags", "--compiler-filter=speed")
        }
        
        FirebaseApp.initializeApp(this)
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)
    }
}
