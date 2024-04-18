package com.example.domain.usecase.category

import Category
import com.example.domain.repository.CategoryRepository
import com.example.domain.usecase.Usecase

class setCategoryListUsecase(private val repository : CategoryRepository, val categoryList: List<Category>) : Usecase<Unit> {
    override suspend fun invoke() = repository.setCategoryList(categoryList)
}