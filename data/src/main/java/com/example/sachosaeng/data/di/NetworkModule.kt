package com.example.sachosaeng.data.di

import com.example.sachosaeng.data.BuildConfig
import com.example.sachosaeng.data.remote.oauth.OAuthHeaderInterceptor
import com.example.sachosaeng.data.remote.oauth.OAuthenticator
import com.example.sachosaeng.data.remote.util.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }

    @Provides
    fun provideOkHttpClient(
        okhttpClientBuilder: OkHttpClient.Builder,
        oAuthenticator: OAuthenticator
    ): OkHttpClient {
        return okhttpClientBuilder
            .authenticator(oAuthenticator)
            .build()
    }

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

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SachoSaeng

    @Provides
    @Singleton
    @SachoSaeng
    fun provideRetrofit(
        okhttpClientBuilder: OkHttpClient.Builder,
        json: Json,
        oAuthenticator: OAuthenticator,
        oAuthHeaderInterceptor: OAuthHeaderInterceptor,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                okhttpClientBuilder
                    .authenticator(oAuthenticator)
                    .addInterceptor(oAuthHeaderInterceptor)
                    .build()
            )
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
        okhttpClientBuilder: OkHttpClient.Builder,
        json: Json,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                okhttpClientBuilder
                    .build()
            )
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }
}