package com.example.sachosaeng.core.domain.usecase.category

import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.domain.repository.CategoryRepository

class setCategoryListUsecase(private val repository : CategoryRepository, val categoryList: List<Category>) :
    com.example.sachosaeng.core.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.setCategoryList(categoryList)
}