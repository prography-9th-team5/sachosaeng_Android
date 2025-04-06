package com.example.sachosaeng.core.usecase.search

import com.example.sachosaeng.data.repository.search.SearchRepository
import javax.inject.Inject

class SetRecentSearchesUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String) = repository.setRecentSearch(query = query)
}