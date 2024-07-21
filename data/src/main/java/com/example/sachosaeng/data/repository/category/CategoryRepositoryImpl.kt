package com.example.sachosaeng.data.repository.category

import com.example.sachosaeng.core.domain.model.Category
import com.example.sachosaeng.core.domain.repository.CategoryRepository
import com.example.sachosaeng.data.api.CategoryService
import com.example.sachosaeng.data.repository.category.CategoryMapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    val categoryService: CategoryService,
) : CategoryRepository {
    override fun getCategoryList(): Flow<List<Category>> =
        flow { categoryService.getAllCategoryList().getOrThrow().toDomain() }

    override fun getMyCategoryList(): List<Category> {
        TODO("Not yet implemented")
    }

    override fun setCategoryList(categoryList: List<Category>) {
        TODO("Not yet implemented")
    }
}