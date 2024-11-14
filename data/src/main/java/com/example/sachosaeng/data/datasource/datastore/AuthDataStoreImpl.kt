package com.sachosaeng.app.data.datasource.datastore

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sachosaeng.app.core.domain.constant.OAuthType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

private const val USER_ID = "id"
private const val USER_EMAIL = "email"
private const val USER_AUTH_TYPE = "auth_type"
private const val USER_KAKAO_TOKEN = "kakao_token"
private const val USER_ACCESS_TOKEN = "access_token"
private const val USER_REFRESH_TOKEN = "refresh_token"
private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "sachosaeng_auth")

class AuthDataStoreImpl @Inject constructor(
    @ApplicationContext val context: Context
) : AuthDataStore {
    private val dataStore = context.authDataStore
    override suspend fun setUserId(userId: Int): Boolean {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(USER_ID)] = userId
        }.run { return true }
    }

    override suspend fun getUserId() = dataStore.data.map { preferences ->
        preferences[intPreferencesKey(USER_ID)] ?: -1
    }.catch {
        it.printStackTrace()
        emit(-1)
    }.firstOrNull() ?: -1

    override suspend fun setEmail(email: String, type: OAuthType): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_EMAIL)] = email
            preferences[stringPreferencesKey(USER_AUTH_TYPE)] = type.name
        }.run { return true }
    }

    //todo: default 값 지우기
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getEmail() = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_EMAIL)] ?: ""
    }.catch {
        it.printStackTrace()
        emit("")
    }.firstOrNull() ?: ""

    override suspend fun setAccessToken(token: String?): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_ACCESS_TOKEN)] = token ?: ""
        }.run { return true }
    }

    override suspend fun getAccessToken(): String = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_ACCESS_TOKEN)] ?: ""
    }.catch {
        it.printStackTrace()
        emit("")
    }.firstOrNull() ?: ""

    override suspend fun getKakaoAccessToken(): String = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_KAKAO_TOKEN)] ?: ""
    }.catch {
        it.printStackTrace()
        emit("")
    }.firstOrNull() ?: ""

    override suspend fun setRefreshToken(token: String?): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_REFRESH_TOKEN)] = token ?: ""
        }.run { return true }
    }

    override suspend fun clearUserInfo(): Boolean {
        dataStore.edit { preferences ->
            preferences.remove(intPreferencesKey(USER_ID))
            preferences.remove(stringPreferencesKey(USER_EMAIL))
            preferences.remove(stringPreferencesKey(USER_AUTH_TYPE))
            preferences.remove(stringPreferencesKey(USER_ACCESS_TOKEN))
            preferences.remove(stringPreferencesKey(USER_REFRESH_TOKEN))
        }.run { return true }
    }

    override suspend fun getRefreshToken(): String = context.authDataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_REFRESH_TOKEN)] ?: ""
    }.catch {
        it.printStackTrace()
        emit("")
    }.firstOrNull() ?: ""

    override suspend fun getRecentOauthType() = context.authDataStore.data.map { preferences ->
        OAuthType.valueOf(preferences[stringPreferencesKey(USER_AUTH_TYPE)] ?: OAuthType.NONE.name)
    }.catch {
        it.printStackTrace()
        emit(OAuthType.NONE)
    }.firstOrNull() ?: OAuthType.NONE

    override suspend fun setKakaoLoginToken(token: String): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_KAKAO_TOKEN)] = token
        }.run { return true }
    }
}