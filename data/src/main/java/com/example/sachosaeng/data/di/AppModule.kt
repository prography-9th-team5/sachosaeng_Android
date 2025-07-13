package com.example.sachosaeng.data.di

import android.content.Context
import com.example.sachosaeng.data.local.manager.FileManager
import com.example.sachosaeng.data.local.manager.FileManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFileRepository(
        @ApplicationContext context: Context
    ): FileManager = FileManagerImpl(context)
}

