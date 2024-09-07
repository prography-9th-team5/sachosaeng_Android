package com.example.sachosaeng.data.di

import com.example.sachosaeng.data.api.AuthService
import com.example.sachosaeng.data.api.CategoryService
import com.example.sachosaeng.data.api.OAuthService
import com.example.sachosaeng.data.api.UserService
import com.example.sachosaeng.data.api.VoteService
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
    internal fun provideVoteService(
        @NetworkModule.SachoSaeng retrofit: Retrofit
    ): VoteService = retrofit.create(VoteService::class.java)

    @Provides
    internal fun provideAuthService(
        @NetworkModule.SachoSaeng retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)

//    @Provides
//    internal fun provideOAuthService(
//        @NetworkModule.OAuth retrofit: Retrofit
//    ): OAuthService = retrofit.create(OAuthService::class.java)

    @Provides
    internal fun provideUserService(
        @NetworkModule.SachoSaeng retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)
}