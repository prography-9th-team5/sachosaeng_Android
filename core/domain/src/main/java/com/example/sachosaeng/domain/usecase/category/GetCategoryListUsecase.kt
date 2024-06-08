package com.example.sachosaeng.domain.usecase.category

import Category
import com.example.sachosaeng.domain.repository.CategoryRepository
import com.example.sachosaeng.domain.usecase.Usecase

class GetCategoryListUsecase(private val repository : com.example.sachosaeng.domain.repository.CategoryRepository) :
    com.example.sachosaeng.domain.usecase.Usecase<List<Category>> {
    override suspend fun invoke() = repository.getCategoryList()
}