package com.example.sachosaeng.data.di

import com.example.sachosaeng.data.api.AuthService
import com.example.sachosaeng.data.api.CategoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {
    @Provides
    internal fun provideCategoryService(
        @NetworkModule.SachoSaeng retrofit: Retrofit
    ): CategoryService = retrofit.create(CategoryService::class.java)

    @Provides
    internal fun provideAuthService(
        @NetworkModule.SachoSaeng retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)
}