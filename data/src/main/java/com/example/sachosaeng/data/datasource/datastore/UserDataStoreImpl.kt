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

private const val USER_TYPE = "user_type"
private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "sachosaeng_user")

class UserDataStoreImpl @Inject constructor(
    @ApplicationContext val context: Context
) : UserDataStore {
    private val dataStore = context.userDataStore

    override suspend fun setUserType(userType: String): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_TYPE)] = userType
        }.run { return true }
    }

    override suspend fun getUserType() = context.userDataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_TYPE)] ?: ""
    }.catch {
        it.printStackTrace()
        emit("")
    }.first()
}