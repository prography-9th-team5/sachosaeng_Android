package com.example.sachosaeng.data.di

import com.example.sachosaeng.data.remote.oauth.OAuthRepository
import com.example.sachosaeng.data.remote.oauth.OAuthRepositoryImpl
import com.example.sachosaeng.data.repository.article.ArticleRepository
import com.example.sachosaeng.data.repository.article.ArticleRepositoryImpl
import com.example.sachosaeng.data.repository.auth.AuthRepository
import com.example.sachosaeng.data.repository.auth.AuthRepositoryImpl
import com.example.sachosaeng.data.repository.bookmark.BookmarkRepository
import com.example.sachosaeng.data.repository.bookmark.BookmarkRepositoryImpl
import com.example.sachosaeng.data.repository.category.CategoryRepository
import com.example.sachosaeng.data.repository.category.CategoryRepositoryImpl
import com.example.sachosaeng.data.repository.user.UserRepository
import com.example.sachosaeng.data.repository.user.UserRepositoryImpl
import com.example.sachosaeng.data.repository.vote.VoteRepository
import com.example.sachosaeng.data.repository.vote.VoteRepositoryImpl
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