package com.sachosaeng.app.core.util

import android.content.Context
import com.sachosaeng.app.core.util.manager.DateManager
import com.sachosaeng.app.core.util.manager.DeviceManager
import com.sachosaeng.app.core.util.manager.PackageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CoreSettingModule {
    @Provides
    @Singleton
    fun providesPackageManagerModule(@ApplicationContext context: Context): PackageManager = PackageManager(context)

    @Provides
    @Singleton
    fun providesDeviceManagerModule(@ApplicationContext context: Context): DeviceManager = DeviceManager(context)

    @Provides
    @Singleton
    fun providesDateManagerModule(): DateManager = DateManager()
}

