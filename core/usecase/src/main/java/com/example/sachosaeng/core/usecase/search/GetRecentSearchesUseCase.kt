package com.example.sachosaeng.core.usecase.search

import com.example.sachosaeng.data.repository.search.SearchRepository
import javax.inject.Inject

class GetRecentSearchesUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke() = repository.getRecentSearch()
}