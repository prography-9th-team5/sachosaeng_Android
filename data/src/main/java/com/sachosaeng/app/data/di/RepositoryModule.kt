package com.sachosaeng.app.data.di

import com.sachosaeng.app.data.remote.oauth.OAuthRepository
import com.sachosaeng.app.data.remote.oauth.OAuthRepositoryImpl
import com.sachosaeng.app.data.repository.article.ArticleRepository
import com.sachosaeng.app.data.repository.article.ArticleRepositoryImpl
import com.sachosaeng.app.data.repository.auth.AuthRepository
import com.sachosaeng.app.data.repository.auth.AuthRepositoryImpl
import com.sachosaeng.app.data.repository.bookmark.BookmarkRepository
import com.sachosaeng.app.data.repository.bookmark.BookmarkRepositoryImpl
import com.sachosaeng.app.data.repository.category.CategoryRepository
import com.sachosaeng.app.data.repository.category.CategoryRepositoryImpl
import com.sachosaeng.app.data.repository.user.UserRepository
import com.sachosaeng.app.data.repository.user.UserRepositoryImpl
import com.sachosaeng.app.data.repository.vote.VoteRepository
import com.sachosaeng.app.data.repository.vote.VoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindVoteRepository(
        voteRepositoryImpl: VoteRepositoryImpl
    ): VoteRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindOAuthRepository(
        oAuthRepositoryImpl: OAuthRepositoryImpl
    ): OAuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        bookmarkRepositoryImpl: BookmarkRepositoryImpl
    ): BookmarkRepository

    @Binds
    @Singleton
    abstract fun bindArticleRepository(
        articleRepositoryImpl: ArticleRepositoryImpl
    ): ArticleRepository
}