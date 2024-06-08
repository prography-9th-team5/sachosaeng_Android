package com.example.sachosaeng.domain.usecase.category

import Category
import com.example.sachosaeng.domain.repository.CategoryRepository
import com.example.sachosaeng.domain.usecase.Usecase

class setCategoryListUsecase(private val repository : com.example.sachosaeng.domain.repository.CategoryRepository, val categoryList: List<Category>) :
    com.example.sachosaeng.domain.usecase.Usecase<Unit> {
    override suspend fun invoke() = repository.setCategoryList(categoryList)
}