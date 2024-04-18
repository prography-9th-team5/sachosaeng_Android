package com.example.domain.usecase.category

import Category
import com.example.domain.repository.CategoryRepository
import com.example.domain.usecase.Usecase

class GetCategoryListUsecase(private val repository : CategoryRepository) : Usecase<List<Category>> {
    override suspend fun invoke() = repository.getCategoryList()
}