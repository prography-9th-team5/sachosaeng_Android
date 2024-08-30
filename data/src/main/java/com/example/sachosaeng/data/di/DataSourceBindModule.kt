package com.example.sachosaeng.data.di

import com.example.sachosaeng.data.datasource.datastore.AuthDataStore
import com.example.sachosaeng.data.datasource.datastore.AuthDataStoreImpl
import com.example.sachosaeng.data.datasource.datastore.UserDataStore
import com.example.sachosaeng.data.datasource.datastore.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceBindModule {

    @Binds
    @Singleton
    abstract fun bindAuthDataStore(
        authDataStoreImpl: AuthDataStoreImpl
    ): AuthDataStore

    @Binds
    @Singleton
    abstract fun bindUserDataStore(
        userDataStoreImpl: UserDataStoreImpl
    ): UserDataStore
}