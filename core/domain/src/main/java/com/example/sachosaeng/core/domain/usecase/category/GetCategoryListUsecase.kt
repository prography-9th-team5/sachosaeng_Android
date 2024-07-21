package com.example.sachosaeng.core.domain.usecase.category

import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.domain.repository.CategoryRepository
import com.example.sachosaeng.core.domain.usecase.Usecase
import kotlinx.coroutines.flow.Flow


class GetCategoryListUsecase(private val repository : CategoryRepository) :
    Usecase<Flow<List<Category>>> {
    override suspend fun invoke() = repository.getCategoryList()
}