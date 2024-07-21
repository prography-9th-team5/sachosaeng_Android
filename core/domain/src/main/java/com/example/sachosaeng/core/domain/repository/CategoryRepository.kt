package com.example.sachosaeng.core.domain.repository

import com.example.sachosaeng.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategoryList(): Flow<List<Category>>
    fun getMyCategoryList(): List<Category>
    fun setCategoryList(categoryList: List<Category>)
}