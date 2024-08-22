package com.example.sachosaeng.data.di

import com.example.sachosaeng.data.api.CategoryService
import com.example.sachosaeng.data.datasource.RemoteCategoryDataSource
import com.example.sachosaeng.data.datasource.RemoteCategoryDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class RemoteDataSourceModule {
    @Provides
    fun provideRemoteCategoryDataSource(
        categoryService: CategoryService
    ): RemoteCategoryDataSource {
        return RemoteCategoryDataSourceImpl(categoryService)
    }
}