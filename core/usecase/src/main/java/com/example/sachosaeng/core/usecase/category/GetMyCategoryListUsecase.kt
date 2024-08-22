package com.example.sachosaeng.core.usecase.category

import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.usecase.Usecase
import com.example.sachosaeng.data.repository.category.CategoryRepository

class GetMyCategoryListUsecase(private val repository: CategoryRepository) :
    Usecase<List<Category>> {
    override suspend fun invoke() = repository.getMyCategoryList()
}