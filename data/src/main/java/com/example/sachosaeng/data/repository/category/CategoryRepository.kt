package com.example.sachosaeng.data.repository.category

import com.example.sachosaeng.core.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategoryList(): Flow<List<Category>>
    fun getMyCategoryList(): List<Category>
    fun setCategoryList(categoryList: List<Category>): Flow<Unit>
}