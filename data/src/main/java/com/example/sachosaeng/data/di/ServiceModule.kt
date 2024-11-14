package com.sachosaeng.app.data.di

import com.sachosaeng.app.data.api.ArticleService
import com.sachosaeng.app.data.api.AuthService
import com.sachosaeng.app.data.api.BookmarkService
import com.sachosaeng.app.data.api.CategoryService
import com.sachosaeng.app.data.api.OAuthService
import com.sachosaeng.app.data.api.UserService
import com.sachosaeng.app.data.api.VoteService
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

    @Provides
    internal fun provideArticleService(
        @NetworkModule.SachoSaeng retrofit: Retrofit
    ): ArticleService = retrofit.create(ArticleService::class.java)

    @Provides
    internal fun provideOAuthService(
        @NetworkModule.OAuth retrofit: Retrofit
    ): OAuthService = retrofit.create(OAuthService::class.java)

    @Provides
    internal fun provideUserService(
        @NetworkModule.SachoSaeng retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)

    @Provides
    internal fun provideBookmarkService(
        @NetworkModule.SachoSaeng retrofit: Retrofit
    ): BookmarkService = retrofit.create(BookmarkService::class.java)
}