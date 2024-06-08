package com.example.sachosaeng.domain.repository

import Category

interface CategoryRepository {
    suspend fun getCategoryList(): List<Category>
    suspend fun getMyCategoryList(): List<Category>
    suspend fun setCategoryList(categoryList: List<Category>)
}