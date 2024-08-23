package com.example.sachosaeng.core.usecase.category

import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryListUsecase @Inject constructor(private val repository: CategoryRepository) :
    Usecase<Flow<List<Category>>> {
    override suspend fun invoke() = repository.getCategoryList()
}