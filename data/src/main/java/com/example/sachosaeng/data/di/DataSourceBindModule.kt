package com.sachosaeng.app.data.di

import com.sachosaeng.app.data.datasource.datastore.AuthDataStore
import com.sachosaeng.app.data.datasource.datastore.AuthDataStoreImpl
import com.sachosaeng.app.data.datasource.datastore.UserDataStore
import com.sachosaeng.app.data.datasource.datastore.UserDataStoreImpl
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