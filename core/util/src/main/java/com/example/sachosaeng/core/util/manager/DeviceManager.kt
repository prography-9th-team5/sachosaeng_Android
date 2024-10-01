package com.example.sachosaeng.core.util.manager

import android.content.Context
import android.os.Process
import android.provider.Settings.Secure.ANDROID_ID
import android.provider.Settings.Secure.getString
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.system.exitProcess

class DeviceManager(
    @ApplicationContext private val context: Context
) {
    fun finishApp() {
        Process.killProcess(Process.myPid())
        exitProcess(0)
    }

    fun getDeviceId(): String {
        return getString(context.contentResolver, ANDROID_ID)
    }
}