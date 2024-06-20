package com.example.sachosaeng.core.domain.usecase.category

import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.domain.repository.CategoryRepository
import com.example.sachosaeng.core.domain.usecase.Usecase


class GetCategoryListUsecase(private val repository : CategoryRepository) :
    Usecase<List<Category>> {
    override suspend fun invoke() = repository.getCategoryList()
}