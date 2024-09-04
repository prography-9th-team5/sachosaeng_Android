package com.example.sachosaeng.data.di

import android.content.Context
import com.example.sachosaeng.data.BuildConfig
import com.example.sachosaeng.data.remote.oauth.OAuthHeaderInterceptor
import com.example.sachosaeng.data.remote.oauth.OAuthenticator
import com.example.sachosaeng.data.remote.util.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            prettyPrint = true
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        oAuthenticator: OAuthenticator,
        oAuthHeaderInterceptor: OAuthHeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .authenticator(oAuthenticator)
            .addInterceptor(oAuthHeaderInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SachoSaeng

    @Provides
    @Singleton
    @SachoSaeng
    fun provideSachoSaengRetrofit(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OAuth

    @Provides
    @Singleton
    @OAuth
    fun provideOAuthRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }
}