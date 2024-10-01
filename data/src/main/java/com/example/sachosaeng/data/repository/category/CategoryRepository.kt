package com.sachosaeng.app.data.repository.category

import com.sachosaeng.app.core.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategoryList(): Flow<List<Category>>
    fun getAllCategoryIcon(): Flow<Category>
    fun getMyCategoryList(): Flow<List<Category>>
    fun setCategoryList(categoryList: List<Category>): Flow<Unit>
}