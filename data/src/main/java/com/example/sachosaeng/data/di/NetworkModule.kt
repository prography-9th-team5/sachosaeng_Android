package com.example.sachosaeng.data.di

import android.content.Context
import com.example.sachosaeng.data.BuildConfig
import com.example.sachosaeng.data.remote.oauth.OAuthHeaderInterceptor
import com.example.sachosaeng.data.remote.oauth.OAuthenticator
import com.example.sachosaeng.data.remote.util.CallAdapterFactory
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
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }

    @Provides
    fun provideOkHttpClient(okhttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return okhttpClientBuilder.build()
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

    @Provides
    @Singleton
    fun provideAuthHeaderInterceptor(): OAuthHeaderInterceptor = OAuthHeaderInterceptor()

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SachoSaeng

    @Provides
    @Singleton
    @SachoSaeng
    fun provideRetrofit(
        @ApplicationContext context: Context,
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
            .addCallAdapterFactory(CallAdapterFactory())
            .build()
    }
}