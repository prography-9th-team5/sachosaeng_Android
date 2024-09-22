package com.example.sachosaeng.core.util.manager

import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import android.os.Process
import kotlin.system.exitProcess
import android.provider.Settings.Secure.ANDROID_ID
import android.provider.Settings.Secure.getString

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