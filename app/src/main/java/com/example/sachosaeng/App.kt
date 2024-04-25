package com.example.sachosaeng

import android.app.Application
import com.kakao.sdk.common.KakaoSdk


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)
    }
}
