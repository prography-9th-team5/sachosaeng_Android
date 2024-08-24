package com.example.sachosaeng.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val USER_EMAIL = "email"
private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "sachosaeng_auth")

class AuthDataStoreImpl @Inject constructor(
    @ApplicationContext val context: Context
) : AuthDataStore {
    private val dataStore = context.authDataStore

    override suspend fun setEmail(email: String): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_EMAIL)] = email
        }.run { return true }
    }

    //todo: default 값 지우기
    override suspend fun getEmail() = context.authDataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_EMAIL)] ?: "test1@test"
    }.catch {
        it.printStackTrace()
        emit("")
    }.first()
}