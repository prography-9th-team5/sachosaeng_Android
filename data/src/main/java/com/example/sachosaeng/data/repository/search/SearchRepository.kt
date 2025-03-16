package com.example.sachosaeng.data.repository.search

import com.sachosaeng.app.core.model.VoteList
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchResult(query: String): Flow<List<VoteList?>>?
    fun getRecentSearch(): Flow<List<String?>>?
    suspend fun setRecentSearch(query: String)
}