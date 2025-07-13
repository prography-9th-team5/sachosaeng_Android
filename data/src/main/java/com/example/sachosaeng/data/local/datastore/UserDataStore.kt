package com.sachosaeng.app.data.datasource.datastore

import kotlinx.coroutines.flow.Flow

interface UserDataStore {
    suspend fun setUserType(type: String): Boolean
    suspend fun getUserType(): String
    fun getRecentSearch(): Flow<List<String>>
    suspend fun setRecentSearches(search: String)
    suspend fun removeRecentSearch(search: String)
    suspend fun clearSearchHistory()
    suspend fun setUserNickName(name: String)
    suspend fun getUserNickName(): String
}