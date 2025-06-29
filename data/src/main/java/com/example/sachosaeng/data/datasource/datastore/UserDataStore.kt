package com.sachosaeng.app.data.datasource.datastore

import kotlinx.coroutines.flow.Flow

interface UserDataStore {
    suspend fun setUserType(type: String): Boolean
    suspend fun getUserType(): String
    suspend fun setUserGrowthSystemConfirmed(confirmed: Boolean): Unit
    suspend fun getUserGrowthSystemConfirmed(): Boolean
    fun getRecentSearch(): Flow<List<String>>
    suspend fun setRecentSearches(search: String)
    suspend fun removeRecentSearch(search: String)
    suspend fun clearSearchHistory()
}