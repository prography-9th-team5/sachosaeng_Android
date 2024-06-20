package com.example.sachosaeng.core.domain.repository

import com.example.sachosaeng.core.domain.model.Category

interface CategoryRepository {
    suspend fun getCategoryList(): List<Category>
    suspend fun getMyCategoryList(): List<Category>
    suspend fun setCategoryList(categoryList: List<Category>)
}