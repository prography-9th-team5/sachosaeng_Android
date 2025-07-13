package com.sachosaeng.app.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val RECENT_SEARCH = "recent_search"
private val DELIMITER = ","
private const val USER_TYPE = "user_type"
private const val USER_NAME = "user_name"
private const val USER_GROWTH_SYSTEM_CONFIRMED = "user_growth_system_confirmed"

private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "sachosaeng_user")

class UserDataStoreImpl @Inject constructor(
    @ApplicationContext val context: Context
) : UserDataStore {
    private val dataStore = context.userDataStore

    override suspend fun setUserType(type: String): Boolean {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_TYPE)] = type
        }.run { return true }
    }

    override suspend fun getUserType() = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_TYPE)] ?: ""
    }.catch {
        it.printStackTrace()
        emit("")
    }.firstOrNull() ?: ""

    override suspend fun getUserGrowthSystemConfirmed(): Boolean = dataStore.data.map { preferences ->
        preferences[booleanPreferencesKey(USER_GROWTH_SYSTEM_CONFIRMED)] ?: false
    }.catch {
        it.printStackTrace()
        emit(false)
    }.firstOrNull() ?: false

    override suspend fun setUserGrowthSystemConfirmed(confirmed: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(USER_GROWTH_SYSTEM_CONFIRMED)] = confirmed
        }.run { return }
    }

    override suspend fun setRecentSearches(search: String) {
        dataStore.edit { preferences ->
            val currentList = getSearchHistorySync(preferences)
            val newList = (listOf(search) + currentList).distinct().take(10)
            preferences[stringPreferencesKey(RECENT_SEARCH)] = newList.joinToString(DELIMITER)
        }
    }

    override suspend fun removeRecentSearch(search: String) {
        dataStore.edit { preferences ->
            val currentList = getSearchHistorySync(preferences)
            val newList = (currentList - setOf(search)).distinct().take(10)
            preferences[stringPreferencesKey(RECENT_SEARCH)] = newList.joinToString(DELIMITER)
        }
    }

    override fun getRecentSearch(): Flow<List<String>> {
        return dataStore.data.map { preferences ->
            getSearchHistorySync(preferences)
        }
    }

    override suspend fun clearSearchHistory() {
        dataStore.edit { it.remove(stringPreferencesKey(RECENT_SEARCH)) }
    }

    private fun getSearchHistorySync(preferences: Preferences): List<String> {
        return preferences[stringPreferencesKey(RECENT_SEARCH)]?.split(DELIMITER)
            ?.filter { it.isNotBlank() } ?: emptyList()
    }

    override suspend fun setUserNickName(name: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_NAME)] = name
        }
    }

    override suspend fun getUserNickName() : String  = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(USER_NAME)] ?: ""
    }.catch {
        it.printStackTrace()
        System.currentTimeMillis().toString()
    }.firstOrNull() ?: System.currentTimeMillis().toString()
}