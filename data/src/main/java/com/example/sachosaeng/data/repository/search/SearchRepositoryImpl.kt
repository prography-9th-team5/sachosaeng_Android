package com.example.sachosaeng.data.repository.search

import com.example.sachosaeng.data.repository.search.SearchMapper.toDomain
import com.sachosaeng.app.core.model.VoteList
import com.sachosaeng.app.data.api.SearchService
import com.sachosaeng.app.data.datasource.datastore.UserDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService,
    private val userDataStore: UserDataStore
): SearchRepository {
    override fun getSearchResult(query: String): Flow<List<VoteList?>> = flow {
        searchService.getSearchResult(query).getOrNull()?.data?.toDomain()?.let { emit(it) }
    }
    override fun getRecentSearch(): Flow<List<String?>> = userDataStore.getRecentSearch()
    override suspend fun setRecentSearch(query: String) = userDataStore.setRecentSearches(query)
    override suspend fun removeRecentSearch(query: String) = userDataStore.removeRecentSearch(query)
}