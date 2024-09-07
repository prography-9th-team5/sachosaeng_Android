package com.example.sachosaeng.data.repository.category

import com.example.sachosaeng.core.model.Category
import com.example.sachosaeng.data.api.CategoryService
import com.example.sachosaeng.data.repository.category.CategoryMapper.toDomain
import com.example.sachosaeng.data.repository.category.CategoryMapper.toRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    val categoryService: CategoryService,
) : CategoryRepository {
    override fun getCategoryList(): Flow<List<Category>> =
        flow { categoryService.getAllCategoryList().getOrThrow().data?.let { emit(it.toDomain()) } }

    override fun getAllCategoryIcon(): Flow<Category> = flow {
        categoryService.getAllCategoryIcon().getOrThrow().data?.let { emit(it.toDomain()) }
    }

    override fun getMyCategoryList(): Flow<List<Category>> = flow {
        categoryService.getMyCategoryList().getOrThrow().data?.let { emit(it.toDomain()) }
    }

    override fun setCategoryList(categoryList: List<Category>): Flow<Unit> = flow {
       categoryService.setMyCategoryList(categoryList.toRequest()).getOrThrow().data?.let { emit(Unit) }
    }
}