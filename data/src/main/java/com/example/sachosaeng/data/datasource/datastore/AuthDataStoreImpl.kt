package com.example.sachosaeng.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val USER_ID = "id"
private const val USER_EMAIL = "email"
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

    override suspend fun getUserId() = context.authDataStore.data.map { preferences ->
        preferences[intPreferencesKey(USER_ID)] ?: -1
    }.catch {
        it.printStackTrace()
        emit(-1)
    }.first()

    override suspend fun setEmail(email: String): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_EMAIL)] = email
        }.run { return true }
    }

    //todo: default 값 지우기
    override suspend fun getEmail() = context.authDataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_EMAIL)] ?: "testtest19@naver.com"
    }.catch {
        it.printStackTrace()
        emit("")
    }.first()

    override suspend fun setAccessToken(token: String): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_ACCESS_TOKEN)] = token
        }.run { return true }
    }

    override suspend fun getAccessToken(): String = context.authDataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_ACCESS_TOKEN)] ?: ""
    }.catch {
        it.printStackTrace()
        emit("")
    }.first()

    override suspend fun setRefreshToken(token: String): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_REFRESH_TOKEN)] = token
        }.run { return true }
    }

    override suspend fun getRefreshToken(): String = context.authDataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_REFRESH_TOKEN)] ?: ""
    }.catch {
        it.printStackTrace()
        emit("")
    }.first()
}