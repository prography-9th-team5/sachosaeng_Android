package com.example.data.repository

import Category
import com.example.domain.repository.CategoryRepository

class CategoryRepositoryImpl: CategoryRepository {
    override suspend fun getCategoryList(): List<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun getMyCategoryList(): List<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun setCategoryList(categoryList: List<Category>) {
        TODO("Not yet implemented")
    }
}