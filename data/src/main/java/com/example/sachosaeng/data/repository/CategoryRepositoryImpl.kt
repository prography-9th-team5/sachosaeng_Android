package com.example.sachosaeng.data.repository

import Category
import com.example.sachosaeng.domain.repository.CategoryRepository

class CategoryRepositoryImpl: com.example.sachosaeng.domain.repository.CategoryRepository {
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