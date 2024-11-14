package com.sachosaeng.app.core.util.manager

import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import android.provider.Settings.Secure.ANDROID_ID
import android.provider.Settings.Secure.getString

class PackageManager(
    @ApplicationContext private val context: Context
) {
    private fun getPackageName(): String {
        return context.packageName
    }

    fun getVersionName(): String {
        return context.packageManager.getPackageInfo(getPackageName(), 0).versionName
    }

    fun getVersionCode(): Int {
        return context.packageManager.getPackageInfo(getPackageName(), 0).versionCode
    }
}